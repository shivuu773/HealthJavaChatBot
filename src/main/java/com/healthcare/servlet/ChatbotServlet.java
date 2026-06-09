package com.healthcare.servlet;

import com.google.gson.*;
import com.healthcare.dao.AppointmentDAO;
import com.healthcare.dao.DiseaseDAO;
import com.healthcare.db.DatabaseInitializer;
import com.healthcare.model.Disease;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class ChatbotServlet extends HttpServlet {

    private DiseaseDAO diseaseDAO;
    private AppointmentDAO appointmentDAO;
    private Gson gson;

    @Override
    public void init() throws ServletException {
        DatabaseInitializer.initialize();
        diseaseDAO = new DiseaseDAO();
        appointmentDAO = new AppointmentDAO();
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=UTF-8");
        resp.setHeader("Access-Control-Allow-Origin", "*");

        String userMessage = req.getParameter("message");
        String sessionId = req.getSession().getId();

        if (userMessage == null || userMessage.trim().isEmpty()) {
            resp.getWriter().write("{\"response\":\"Please type a message.\",\"type\":\"error\"}");
            return;
        }

        String botResponse = processMessage(userMessage.trim());
        String type = detectResponseType(userMessage);

        // Log to DB
        appointmentDAO.saveChatLog(sessionId, userMessage, botResponse);

        JsonObject json = new JsonObject();
        json.addProperty("response", botResponse);
        json.addProperty("type", type);
        json.addProperty("userMessage", userMessage);

        resp.getWriter().write(gson.toJson(json));
    }

    private String processMessage(String msg) {
        String lower = msg.toLowerCase();

        // Greetings
        if (matches(lower, "hello","hi","hey","good morning","good evening","good afternoon","greetings")) {
            return "👋 Hello! Welcome to **HealthCare Chatbot**! I'm your personal health assistant.\n\nI can help you with:\n• 🔍 Disease information & symptoms\n• 💊 Medicine recommendations\n• 📅 Appointment booking guidance\n• 🏥 Hospital contact info\n• ⚠️ Emergency guidance\n• 🛡️ Health precautions & prevention tips\n\nHow can I assist you today?";
        }

        // Emergency keywords
        if (matches(lower, "emergency","chest pain","can't breathe","heart attack","stroke","unconscious","bleeding heavily","seizure","overdose")) {
            return "🚨 **EMERGENCY ALERT!**\n\n⚠️ This sounds like a medical emergency. Please:\n\n1. **Call 108 (Ambulance)** immediately\n2. **Call 102 (Emergency)** for urgent help\n3. Stay calm and keep the patient still\n4. Do NOT give food or water\n5. Stay on the phone with emergency services\n\n🏥 **Emergency Contacts:**\n• National Emergency: 112\n• Ambulance: 108\n• Poison Control: 1800-116-117\n\n⚕️ Get to the nearest emergency room NOW!";
        }

        // Appointment booking
        if (matches(lower, "book appointment","schedule appointment","make appointment","appointment","book doctor","see doctor","visit doctor")) {
            return "📅 **Book an Appointment**\n\nTo book a doctor's appointment, please:\n\n1. Click **'Book Appointment'** in the navigation menu\n2. Fill in your details:\n   • Your name, email, phone\n   • Select department & preferred doctor\n   • Choose date and time\n   • Describe your symptoms\n3. Submit the form\n\n🏥 **Available Departments:**\n• General Medicine\n• Cardiology\n• Neurology\n• Orthopedics\n• Dermatology\n• Psychiatry\n• Pulmonology\n• Gastroenterology\n• Endocrinology\n\n⏰ Working Hours: Mon-Sat, 8 AM – 8 PM";
        }

        // Hospital contact
        if (matches(lower, "contact","hospital","address","phone","location","direction","where is")) {
            return "🏥 **HealthCare Hospital - Contact Information**\n\n📍 **Address:** 123 Medical Center Road, Health City - 400001\n\n📞 **Phone Numbers:**\n• Reception: +91-22-1234-5678\n• Emergency: +91-22-1234-5679 (24/7)\n• Appointment: +91-22-1234-5680\n\n📧 **Email:** info@healthcarehospital.com\n\n⏰ **Working Hours:**\n• OPD: Mon-Sat, 8 AM – 8 PM\n• Emergency: 24 × 7\n• Lab: Mon-Sat, 7 AM – 9 PM\n\n🌐 **Departments:** General Medicine | Cardiology | Neurology | Orthopedics | Dermatology | Psychiatry";
        }

        // Health reminders
        if (matches(lower, "reminder","health tip","daily tip","wellness tip","health advice","stay healthy","fitness","lifestyle")) {
            String[] tips = {
                "💧 **Daily Hydration:** Drink 8-10 glasses of water daily to keep your body hydrated and flush toxins.",
                "🏃 **Exercise Regularly:** Aim for 30 minutes of moderate exercise 5 days a week to maintain cardiovascular health.",
                "😴 **Quality Sleep:** Adults need 7-8 hours of sleep per night. Consistent sleep schedules improve overall health.",
                "🥦 **Balanced Diet:** Include fruits, vegetables, whole grains, and lean proteins in every meal for complete nutrition.",
                "🧘 **Stress Management:** Practice meditation, deep breathing, or yoga daily to manage stress and improve mental health.",
                "🚭 **Avoid Tobacco:** Smoking increases risk of cancer, heart disease, and lung disease by over 50%. Quit today!",
                "🩺 **Regular Checkups:** Annual health checkups catch diseases early when they're most treatable. Don't skip them!",
                "☀️ **Vitamin D:** Get 15-20 minutes of morning sunlight daily for Vitamin D, essential for bone and immune health."
            };
            Random r = new Random();
            return "💡 **Health Reminder of the Day**\n\n" + tips[r.nextInt(tips.length)] + "\n\n🌟 Small healthy habits practiced daily lead to extraordinary long-term health outcomes!";
        }

        // Symptom checker trigger words
        if (matches(lower, "symptom","i have","i feel","suffering from","diagnosed with","i am experiencing","feeling","pain in","i got")) {
            return checkSymptoms(lower);
        }

        // Medicine/treatment queries
        if (matches(lower, "medicine","medication","treatment","drug","tablet","capsule","cure","remedy","what to take","what should i take")) {
            return "💊 **Medicine Information**\n\nI can provide general OTC medicine guidance. Please type the **disease name** for specific medicine recommendations.\n\n⚠️ **Important Disclaimer:**\n• All prescription medications require a valid doctor's prescription\n• Never self-medicate for serious conditions\n• Inform your doctor about all current medications\n• Check for allergies before taking any medicine\n\n🔍 Try asking:\n• 'Medicines for Common Cold'\n• 'Treatment for Diabetes'\n• 'What medicines for Migraine?'\n\nOr click **'Diseases'** in the menu to browse all conditions.";
        }

        // Prevention queries
        if (matches(lower, "prevent","prevention","precaution","protect","avoid","safe","safety")) {
            return "🛡️ **Health Prevention & Precautions**\n\nGeneral prevention guidelines:\n\n✅ **Daily Hygiene:**\n• Wash hands with soap for 20+ seconds\n• Brush teeth twice daily\n• Shower regularly\n\n✅ **Diet & Nutrition:**\n• Eat balanced, nutritious meals\n• Avoid excessive sugar and processed foods\n• Stay hydrated\n\n✅ **Vaccinations:**\n• Keep all vaccinations up to date\n• Annual flu vaccine recommended\n• COVID-19 booster shots as advised\n\n✅ **Lifestyle:**\n• Regular exercise (30 min/day)\n• 7-8 hours of quality sleep\n• Manage stress levels\n• No smoking or excessive alcohol\n\n🔍 Ask me about prevention for a specific disease!";
        }

        // Direct disease name lookup
        Disease disease = findDiseaseInMessage(lower);
        if (disease != null) {
            return formatDiseaseResponse(disease);
        }

        // Default response with suggestions
        return "🤔 I didn't quite understand that. Let me help you!\n\nYou can ask me about:\n\n🦠 **Diseases:** 'Tell me about Diabetes' | 'What is COVID-19?'\n🤧 **Symptoms:** 'I have fever and cough' | 'I feel dizzy'\n💊 **Medicines:** 'Medicines for Malaria' | 'Treatment for Asthma'\n📅 **Appointments:** 'Book an appointment'\n🏥 **Hospital:** 'Contact information' | 'Hospital address'\n⚠️ **Emergency:** 'Emergency help'\n💡 **Health Tips:** 'Health reminders'\n\n🔍 Or use the **Search** feature to find any disease!\n\nType your question and I'll do my best to help! 😊";
    }

    private String checkSymptoms(String msg) {
        // Symptom keyword map
        Map<String, String[]> symptomMap = new LinkedHashMap<>();
        symptomMap.put("fever|temperature|hot body", new String[]{"Common Cold","Influenza (Flu)","COVID-19","Dengue","Malaria","Typhoid","Pneumonia"});
        symptomMap.put("cough|coughing|chest", new String[]{"Common Cold","COVID-19","Asthma","Pneumonia"});
        symptomMap.put("runny nose|sneezing|sneeze", new String[]{"Common Cold","Allergies"});
        symptomMap.put("headache|head pain", new String[]{"Migraine","Hypertension","Influenza (Flu)"});
        symptomMap.put("body pain|muscle ache|joint pain", new String[]{"Influenza (Flu)","Dengue","Arthritis","Obesity"});
        symptomMap.put("fatigue|tired|weakness|weak", new String[]{"Anemia","Diabetes","Depression","Obesity"});
        symptomMap.put("thirst|urination|frequent urination", new String[]{"Diabetes"});
        symptomMap.put("stomach|abdominal|gastric|acidity|nausea|vomiting", new String[]{"Gastritis","Food Poisoning","Typhoid"});
        symptomMap.put("diarrhea|loose motion", new String[]{"Food Poisoning"});
        symptomMap.put("breathing|breathless|wheeze|shortness of breath", new String[]{"Asthma","COVID-19","Pneumonia"});
        symptomMap.put("sadness|hopeless|depressed|mood", new String[]{"Depression","Anxiety Disorder"});
        symptomMap.put("worry|anxiety|anxious|panic|nervous", new String[]{"Anxiety Disorder"});
        symptomMap.put("weight|overweight|obese|fat", new String[]{"Obesity","Diabetes"});
        symptomMap.put("rash|skin|itchy|itch", new String[]{"Allergies","Dengue"});
        symptomMap.put("back pain|kidney|urination pain", new String[]{"Kidney Stones"});
        symptomMap.put("pale|pallor|iron|anemia", new String[]{"Anemia"});
        symptomMap.put("chills|sweating|sweat|malaria", new String[]{"Malaria","Influenza (Flu)"});
        symptomMap.put("dizzy|dizziness|blurred vision", new String[]{"Hypertension","Migraine","Anemia"});

        Set<String> matchedDiseases = new LinkedHashSet<>();
        for (Map.Entry<String, String[]> entry : symptomMap.entrySet()) {
            String[] keys = entry.getKey().split("\\|");
            for (String k : keys) {
                if (msg.contains(k)) {
                    matchedDiseases.addAll(Arrays.asList(entry.getValue()));
                    break;
                }
            }
        }

        if (matchedDiseases.isEmpty()) {
            return "🤧 I noticed you're not feeling well. Could you describe your symptoms more specifically?\n\nFor example:\n• 'I have fever, cough and body pain'\n• 'I feel dizzy with headache'\n• 'I have stomach pain and nausea'\n\n⚠️ **Remember:** For accurate diagnosis, always consult a qualified doctor.";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("🔍 **Symptom Analysis Results**\n\n");
        sb.append("Based on your symptoms, possible conditions include:\n\n");
        int count = 0;
        for (String d : matchedDiseases) {
            if (count++ >= 3) break;
            Disease dis = diseaseDAO.getDiseaseByName(d);
            if (dis != null) {
                sb.append("━━━━━━━━━━━━━━━━━━━━\n");
                sb.append("🏥 **").append(dis.getName()).append("**\n");
                sb.append("📋 ").append(dis.getDescription()).append("\n\n");
                String[] syms = dis.getSymptomsArray();
                sb.append("🤧 **Key Symptoms:** ").append(String.join(", ", Arrays.copyOf(syms, Math.min(3, syms.length)))).append("\n");
                String[] meds = dis.getMedicinesArray();
                sb.append("💊 **Medicines:** ").append(String.join(", ", Arrays.copyOf(meds, Math.min(2, meds.length)))).append("\n\n");
            }
        }
        sb.append("━━━━━━━━━━━━━━━━━━━━\n");
        sb.append("⚠️ **Disclaimer:** This is for informational purposes only.\n");
        sb.append("🩺 Please consult a qualified doctor for proper diagnosis and treatment.\n\n");
        sb.append("💬 Type a disease name to get complete information!");
        return sb.toString();
    }

    private Disease findDiseaseInMessage(String msg) {
        String[] diseaseNames = {"common cold","influenza","flu","covid","covid-19","coronavirus","diabetes","hypertension",
            "asthma","migraine","gastritis","dengue","malaria","typhoid","pneumonia","arthritis","anemia","allergies",
            "food poisoning","kidney stones","depression","anxiety","obesity"};
        for (String name : diseaseNames) {
            if (msg.contains(name)) {
                if (name.equals("flu")) return diseaseDAO.getDiseaseByName("Influenza (Flu)");
                if (name.equals("covid") || name.equals("covid-19") || name.equals("coronavirus")) return diseaseDAO.getDiseaseByName("COVID-19");
                if (name.equals("common cold")) return diseaseDAO.getDiseaseByName("Common Cold");
                if (name.equals("anxiety")) return diseaseDAO.getDiseaseByName("Anxiety Disorder");
                if (name.equals("kidney stones")) return diseaseDAO.getDiseaseByName("Kidney Stones");
                if (name.equals("food poisoning")) return diseaseDAO.getDiseaseByName("Food Poisoning");
                List<Disease> results = diseaseDAO.searchDiseases(name);
                if (!results.isEmpty()) return results.get(0);
            }
        }
        return null;
    }

    private String formatDiseaseResponse(Disease d) {
        StringBuilder sb = new StringBuilder();
        sb.append("🏥 **").append(d.getName()).append("**\n\n");
        sb.append("📋 **Description:** ").append(d.getDescription()).append("\n\n");

        sb.append("🤧 **Symptoms:**\n");
        for (String s : d.getSymptomsArray()) sb.append("  • ").append(s.trim()).append("\n");

        sb.append("\n🔬 **Causes:**\n");
        for (String s : d.getCausesArray()) sb.append("  • ").append(s.trim()).append("\n");

        sb.append("\n💊 **Recommended Medicines:**\n");
        for (String s : d.getMedicinesArray()) sb.append("  • ").append(s.trim()).append("\n");

        sb.append("\n⚠️ **Precautions:**\n");
        for (String s : d.getPrecautionsArray()) sb.append("  • ").append(s.trim()).append("\n");

        sb.append("\n🛡️ **Prevention Tips:**\n");
        for (String s : d.getPreventionArray()) sb.append("  • ").append(s.trim()).append("\n");

        sb.append("\n🩺 **Doctor Advice:** ").append(d.getDoctorAdvice()).append("\n\n");

        sb.append("🚨 **Emergency Signs:**\n");
        for (String s : d.getEmergencyArray()) sb.append("  ⚡ ").append(s.trim()).append("\n");

        sb.append("\n━━━━━━━━━━━━━━━━━━━━\n");
        sb.append("⚕️ *This information is educational. Always consult a qualified doctor.*");
        return sb.toString();
    }

    private boolean matches(String text, String... keywords) {
        for (String k : keywords) if (text.contains(k)) return true;
        return false;
    }

    private String detectResponseType(String msg) {
        String lower = msg.toLowerCase();
        if (matches(lower, "emergency","chest pain","heart attack","stroke","unconscious")) return "emergency";
        if (matches(lower, "hello","hi","hey")) return "greeting";
        if (matches(lower, "appointment","book","schedule")) return "appointment";
        return "info";
    }
}
