package com.healthcare.db;

import java.sql.*;

public class DatabaseInitializer {

    public static void initialize() {
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            // Create diseases table
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS diseases (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    name TEXT NOT NULL UNIQUE,
                    description TEXT,
                    symptoms TEXT,
                    causes TEXT,
                    medicines TEXT,
                    precautions TEXT,
                    prevention_tips TEXT,
                    doctor_advice TEXT,
                    emergency_signs TEXT
                )
            """);

            // Create appointments table
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS appointments (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    patient_name TEXT NOT NULL,
                    patient_email TEXT NOT NULL,
                    patient_phone TEXT NOT NULL,
                    doctor_name TEXT,
                    department TEXT,
                    appointment_date TEXT NOT NULL,
                    appointment_time TEXT NOT NULL,
                    symptoms TEXT,
                    message TEXT,
                    status TEXT DEFAULT 'Pending',
                    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
                )
            """);

            // Create chat_logs table
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS chat_logs (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    session_id TEXT,
                    user_message TEXT,
                    bot_response TEXT,
                    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
                )
            """);

            // Seed data
            insertDiseases(conn);
            System.out.println("[DB] Database initialized successfully.");
        } catch (SQLException e) {
            System.err.println("[DB] Initialization error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void insertDiseases(Connection conn) throws SQLException {
        String check = "SELECT COUNT(*) FROM diseases";
        try (Statement s = conn.createStatement(); ResultSet rs = s.executeQuery(check)) {
            if (rs.next() && rs.getInt(1) > 0) return; // already seeded
        }

        String sql = "INSERT OR IGNORE INTO diseases (name,description,symptoms,causes,medicines,precautions,prevention_tips,doctor_advice,emergency_signs) VALUES (?,?,?,?,?,?,?,?,?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            Object[][] data = {
                {"Common Cold",
                 "A viral infection of the upper respiratory tract affecting nose and throat.",
                 "Runny nose|Sneezing|Mild fever|Sore throat|Congestion|Cough",
                 "Rhinovirus|Coronavirus strains|Respiratory syncytial virus|Contact with infected person",
                 "Paracetamol (500mg for fever/pain)|Cetirizine (antihistamine for runny nose)|Steam inhalation|Nasal saline drops|Vitamin C supplements",
                 "Drink warm fluids like ginger tea|Rest adequately (7-8 hours)|Avoid cold beverages|Cover mouth while sneezing|Wash hands frequently",
                 "Get annual flu vaccine|Avoid close contact with sick people|Boost immunity with Vitamin C|Maintain good hygiene",
                 "Consult a doctor if fever exceeds 103°F, symptoms persist beyond 10 days, or difficulty breathing occurs.",
                 "High fever above 104°F|Difficulty breathing|Chest pain|Symptoms lasting more than 10 days"},

                {"Influenza (Flu)",
                 "A contagious respiratory illness caused by influenza viruses affecting nose, throat, and lungs.",
                 "Fever (100-104°F)|Chills|Body pain and muscle aches|Fatigue|Headache|Dry cough",
                 "Influenza A and B viruses|Airborne transmission|Direct contact with infected surfaces",
                 "Paracetamol (for fever and pain)|Ibuprofen (anti-inflammatory)|Antiviral medications (Oseltamivir - doctor prescribed)|Rest and hydration",
                 "Stay hydrated (8-10 glasses of water)|Take sufficient rest|Avoid crowded places|Wear mask in public|Use disposable tissues",
                 "Annual flu vaccination|Frequent handwashing|Avoid touching face|Healthy balanced diet|Regular exercise",
                 "Seek medical care immediately if breathing difficulty, persistent chest pain, or confusion develops.",
                 "Difficulty breathing|Persistent chest pain|Confusion or altered consciousness|Severe vomiting"},

                {"COVID-19",
                 "A respiratory disease caused by SARS-CoV-2 virus, ranging from mild to severe illness.",
                 "Fever or chills|Dry cough|Loss of taste or smell|Breathing difficulty|Fatigue|Body aches|Sore throat",
                 "SARS-CoV-2 coronavirus|Airborne droplets|Close contact with infected person|Contaminated surfaces",
                 "Paracetamol (for fever management)|Doctor-prescribed antiviral medicines|Oxygen therapy (if needed)|Rest and isolation",
                 "Wear N95/surgical masks|Isolate when infected (7-10 days)|Wash hands with soap 20+ seconds|Avoid gatherings|Disinfect surfaces",
                 "Get COVID-19 vaccination and boosters|Maintain 6-feet social distance|Improve ventilation indoors|Avoid non-essential travel",
                 "Seek immediate medical help for breathing difficulty, oxygen levels below 94%, or persistent chest pain.",
                 "Oxygen saturation below 94%|Persistent chest pressure|Bluish lips or face|Inability to stay awake"},

                {"Diabetes",
                 "A chronic metabolic disease causing high blood sugar levels due to insufficient insulin production or resistance.",
                 "Frequent urination (polyuria)|Excessive thirst (polydipsia)|Unexplained weight loss|Fatigue|Blurred vision|Slow-healing wounds",
                 "Type 1: Autoimmune destruction of beta cells|Type 2: Insulin resistance and obesity|Genetic factors|Sedentary lifestyle",
                 "Metformin (Type 2 first-line)|Insulin injections (Type 1 and advanced Type 2 - doctor prescribed)|SGLT2 inhibitors|Blood sugar monitoring",
                 "Monitor blood sugar levels daily|Regular exercise (30 min/day)|Maintain healthy diet (low sugar/carbs)|Take medications on time|Foot care",
                 "Maintain healthy body weight|Regular physical activity|Avoid sugary processed foods|Regular health checkups|Stress management",
                 "Regular monitoring with endocrinologist. Never skip medications. HbA1c checkup every 3 months.",
                 "Blood sugar above 400 mg/dL|Diabetic ketoacidosis symptoms|Unconsciousness|Severe hypoglycemia (shaking, confusion)"},

                {"Hypertension",
                 "A chronic condition where blood pressure in arteries is persistently elevated (above 130/80 mmHg).",
                 "Headache (especially morning)|Dizziness|Blurred vision|Chest pain|Shortness of breath|Nosebleeds",
                 "High salt diet|Obesity|Stress|Sedentary lifestyle|Genetic predisposition|Kidney disease|Age",
                 "Amlodipine (calcium channel blocker)|Losartan (ARB - angiotensin receptor blocker)|Atenolol (beta-blocker)|Diuretics (doctor prescribed)",
                 "Reduce salt intake (less than 5g/day)|Exercise regularly (30 min/day)|Manage stress through meditation|Avoid alcohol and smoking|Monitor BP daily",
                 "DASH diet (fruits, vegetables, low-fat dairy)|Regular aerobic exercise|Maintain healthy weight|Limit alcohol|Quit smoking",
                 "Regular BP monitoring. Consult cardiologist for persistent hypertension. Never stop medications without doctor advice.",
                 "BP above 180/120 mmHg|Severe chest pain|Sudden vision changes|Stroke symptoms (slurred speech, face drooping)"},

                {"Asthma",
                 "A chronic respiratory condition causing airway inflammation, narrowing, and excess mucus production.",
                 "Wheezing (whistling sound while breathing)|Shortness of breath|Chest tightness|Persistent cough (especially at night)|Breathlessness during exercise",
                 "Allergens (dust, pollen, pet dander)|Air pollution|Respiratory infections|Exercise|Cold air|Smoke exposure",
                 "Salbutamol (Albuterol) inhaler - quick relief|Corticosteroid inhalers (Budesonide, Fluticasone - maintenance)|Leukotriene modifiers|Oral corticosteroids (severe attacks)",
                 "Avoid smoke, dust, and pollution|Always carry reliever inhaler|Follow treatment plan strictly|Identify and avoid triggers|Keep indoor air clean",
                 "Use air purifiers at home|Avoid outdoor activity during high pollution|Flu and pneumonia vaccinations|Regular pulmonologist checkups|Breathing exercises",
                 "Seek emergency care during severe attacks. Regular pulmonologist follow-ups. Always have emergency inhaler.",
                 "Severe breathlessness at rest|Blue lips or fingernails|Peak flow below 50%|Inhaler not providing relief"},

                {"Migraine",
                 "A neurological disorder causing recurrent moderate to severe headaches, often with other symptoms.",
                 "Severe throbbing headache (one side)|Nausea and vomiting|Light sensitivity (photophobia)|Sound sensitivity|Visual disturbances (aura)|Dizziness",
                 "Hormonal changes|Stress and anxiety|Poor sleep|Strong smells|Bright lights|Certain foods (caffeine, alcohol, cheese)|Dehydration",
                 "Paracetamol (mild migraines)|Ibuprofen (anti-inflammatory)|Sumatriptan (triptans - doctor prescribed)|Anti-nausea medications|Ergotamine",
                 "Identify and avoid personal triggers|Sleep 7-8 hours regularly|Stay well-hydrated|Rest in dark quiet room during attack|Apply cold/warm compress",
                 "Keep headache diary to identify triggers|Regular sleep schedule|Stress management techniques|Avoid skipping meals|Limit caffeine and alcohol",
                 "Consult neurologist for frequent migraines (>4/month). Preventive medications may be prescribed.",
                 "Worst headache of life|Sudden severe headache|Headache with fever and stiff neck|Headache after head injury|Confusion or vision loss"},

                {"Gastritis",
                 "Inflammation of the stomach lining causing discomfort and digestive issues.",
                 "Stomach pain or burning|Acidity and heartburn|Nausea|Vomiting|Bloating|Loss of appetite|Dark stools",
                 "H. pylori bacterial infection|Prolonged NSAID use|Excessive alcohol|Stress|Autoimmune conditions|Bile reflux",
                 "Omeprazole (proton pump inhibitor)|Antacids (immediate relief)|H2 blockers (Ranitidine)|Antibiotics (if H. pylori - doctor prescribed)|Sucralfate",
                 "Avoid spicy and acidic foods|Eat smaller frequent meals|Reduce caffeine and alcohol|Avoid NSAIDs (aspirin, ibuprofen)|Manage stress",
                 "Eat regularly and on time|Avoid processed and fatty foods|Limit alcohol consumption|Quit smoking|H. pylori testing and treatment",
                 "See gastroenterologist for persistent symptoms, blood in stools, or unexplained weight loss.",
                 "Vomiting blood|Black or tarry stools|Severe abdominal pain|Signs of anemia (pale skin, extreme fatigue)"},

                {"Dengue",
                 "A mosquito-borne viral infection causing flu-like illness, potentially becoming severe.",
                 "High fever (104°F)|Severe joint and muscle pain|Skin rash (appearing 2-5 days after fever)|Headache behind eyes|Nausea|Fatigue",
                 "Dengue virus (DENV 1-4)|Aedes aegypti mosquito bites|Standing water breeding grounds",
                 "Paracetamol (for fever and pain ONLY - avoid ibuprofen/aspirin)|Oral rehydration fluids|IV fluids (if severe)|Platelet transfusion (if needed)",
                 "Prevent mosquito bites (use repellent)|Drink plenty of fluids|Monitor platelet count|Seek medical care promptly|Avoid aspirin/ibuprofen",
                 "Eliminate standing water around home|Use mosquito nets and repellents|Wear long-sleeved clothing|Keep surroundings clean|Get dengue vaccine",
                 "Immediate hospitalization may be required for severe dengue. Monitor platelet count regularly.",
                 "Bleeding from nose or gums|Blood in urine or stools|Rapid drop in platelet count|Severe abdominal pain|Difficulty breathing"},

                {"Malaria",
                 "A life-threatening disease caused by Plasmodium parasites transmitted through infected mosquito bites.",
                 "Fever with chills|Night sweats|Headache|Nausea and vomiting|Muscle aches|Fatigue|Periodic fever cycles",
                 "Plasmodium falciparum, vivax, malariae parasites|Anopheles mosquito bites|Blood transfusion (rare)|Mother to child (rare)",
                 "Antimalarial drugs: Chloroquine, Artemisinin-based therapy (doctor prescribed)|Primaquine (P. vivax)|Supportive care and hydration",
                 "Use insecticide-treated mosquito nets|Apply mosquito repellent (DEET-based)|Avoid outdoor activity at dusk/dawn|Take prophylactic antimalarials if traveling",
                 "Sleep under mosquito nets|Indoor residual spraying|Drain stagnant water|Wear protective long-sleeved clothing|Prophylaxis for travelers",
                 "Seek immediate medical care. Malaria diagnosis requires blood test. Never self-medicate antimalarials.",
                 "Severe anemia|Cerebral malaria (confusion, seizures)|Organ failure|Severe respiratory distress|Unconsciousness"},

                {"Typhoid",
                 "A bacterial infection caused by Salmonella typhi, spread through contaminated food and water.",
                 "High fever (103-104°F)|General weakness and fatigue|Abdominal pain and bloating|Headache|Skin rash (rose spots)|Loss of appetite",
                 "Salmonella typhi bacteria|Contaminated water and food|Poor sanitation|Fecal-oral transmission",
                 "Antibiotics: Ciprofloxacin, Azithromycin, Ceftriaxone (doctor prescribed)|Fever management with Paracetamol|IV fluids if dehydrated|Rest",
                 "Drink only clean boiled or bottled water|Maintain strict hand hygiene|Eat properly cooked food|Avoid street food|Complete antibiotic course",
                 "Typhoid vaccination|Safe drinking water|Proper food sanitation|Handwashing before meals|Safe sewage disposal",
                 "Consult doctor immediately for diagnosis and antibiotic treatment. Complete full antibiotic course even if feeling better.",
                 "Intestinal perforation symptoms (severe abdominal pain)|Internal bleeding|Encephalopathy|Extreme weakness or unconsciousness"},

                {"Pneumonia",
                 "An infection that inflames air sacs in one or both lungs, which may fill with fluid or pus.",
                 "Fever and chills|Persistent cough with phlegm|Chest pain during breathing|Shortness of breath|Fatigue|Nausea|Confusion (elderly)",
                 "Bacteria (Streptococcus pneumoniae)|Viruses (influenza, COVID-19)|Fungi|Aspiration of food/liquids|Weakened immune system",
                 "Antibiotics (Amoxicillin, Azithromycin - doctor prescribed)|Paracetamol (fever and pain)|Cough suppressants|Oxygen therapy (if needed)|Hospitalization",
                 "Get adequate rest|Stay hydrated|Complete full antibiotic course|Avoid smoking|Elevate head while sleeping|Avoid cold air",
                 "Pneumococcal and flu vaccination|Quit smoking|Maintain good hygiene|Boost immunity|Avoid exposure to sick people",
                 "Seek urgent medical attention. May require hospitalization and oxygen therapy. X-ray needed for diagnosis.",
                 "Oxygen saturation below 90%|Bluish lips or skin|Rapid breathing (>30 breaths/min)|Confusion|High fever with shaking chills"},

                {"Arthritis",
                 "Inflammation of one or more joints causing pain, swelling, stiffness, and decreased range of motion.",
                 "Joint pain and tenderness|Swelling around joints|Morning stiffness|Reduced range of motion|Redness and warmth|Fatigue",
                 "Osteoarthritis (joint wear and tear)|Rheumatoid arthritis (autoimmune)|Gout (uric acid crystals)|Age|Obesity|Previous joint injury",
                 "Ibuprofen (anti-inflammatory)|Naproxen (NSAID)|Acetaminophen (pain relief)|DMARDs (Rheumatoid - doctor prescribed)|Joint injections|Physical therapy",
                 "Regular low-impact exercise (swimming, walking)|Maintain healthy weight to reduce joint stress|Hot/cold therapy|Physical therapy|Assistive devices|Rest joints",
                 "Maintain healthy weight|Regular exercise to strengthen muscles around joints|Protect joints during activities|Eat anti-inflammatory diet|Omega-3 fatty acids",
                 "Regular rheumatologist visits. Monitor for systemic complications in rheumatoid arthritis. Never ignore increasing pain.",
                 "Sudden severe joint pain|Joint deformity|Complete loss of joint function|Fever with joint pain (septic arthritis)"},

                {"Anemia",
                 "A condition where you lack enough healthy red blood cells to carry adequate oxygen to body tissues.",
                 "Fatigue and weakness|Pale or yellowish skin|Shortness of breath|Dizziness and headaches|Cold hands and feet|Chest pain|Irregular heartbeat",
                 "Iron deficiency (most common)|Vitamin B12 or folate deficiency|Chronic blood loss|Bone marrow disorders|Chronic diseases|Hemolysis",
                 "Iron supplements (Ferrous sulfate 325mg)|Vitamin B12 supplements (Cyanocobalamin)|Folic acid supplements|IV iron (severe cases)|Blood transfusion (if critical)|Treat underlying cause",
                 "Iron-rich diet (red meat, spinach, lentils)|Vitamin C to enhance iron absorption|Regular health checkups|Take supplements with food|Avoid excessive tea/coffee with meals",
                 "Iron-rich diet from childhood|Regular hemoglobin testing|Treat heavy menstrual bleeding|Prenatal iron supplementation|Vitamin B12-rich foods",
                 "Consult hematologist for proper diagnosis. Blood tests needed to identify type of anemia. Treat underlying cause.",
                 "Hemoglobin below 7 g/dL|Severe chest pain|Extreme shortness of breath at rest|Signs of heart failure"},

                {"Allergies",
                 "Immune system reactions to substances (allergens) that are typically harmless to most people.",
                 "Sneezing and runny nose|Itchy, watery eyes|Skin rashes and hives|Nasal congestion|Itchy throat|Swelling|Difficulty breathing (severe)",
                 "Pollen (seasonal)|Dust mites|Pet dander|Food allergens (nuts, shellfish, dairy)|Insect stings|Medications|Mold",
                 "Cetirizine (antihistamine, non-drowsy)|Loratadine (antihistamine)|Fexofenadine|Nasal corticosteroid sprays|Antihistamine eye drops|Epinephrine (anaphylaxis)",
                 "Identify and avoid allergens|Keep home clean and dust-free|Use hypoallergenic bedding|Shower after outdoor activity|Wear mask during high pollen season",
                 "Allergy testing to identify triggers|Allergen immunotherapy (allergy shots)|Use HEPA air purifiers|Avoid known triggers|Maintain clean indoor environment",
                 "See allergist for proper testing and immunotherapy. Carry epinephrine auto-injector if prescribed.",
                 "Anaphylaxis: throat swelling|Difficulty breathing|Rapid heartbeat|Sudden severe rash|Loss of consciousness - CALL EMERGENCY"},

                {"Food Poisoning",
                 "Illness caused by eating contaminated food containing bacteria, viruses, or toxins.",
                 "Nausea and vomiting|Diarrhea (watery or bloody)|Stomach cramps and pain|Fever|Weakness|Headache",
                 "Bacteria: Salmonella, E. coli, Listeria|Viruses: Norovirus, Hepatitis A|Improper food storage|Undercooked food|Poor hygiene",
                 "Oral Rehydration Solution (ORS) - most important|Anti-diarrheal: Loperamide (not if bloody diarrhea)|Anti-nausea: Ondansetron|Antibiotics (only if bacterial - doctor prescribed)|IV fluids (severe)",
                 "Eat hygienic and properly cooked food|Drink clean filtered water|Refrigerate perishables promptly|Avoid raw or undercooked meat|Wash hands before eating",
                 "Proper food storage and handling|Cook food to safe temperatures|Avoid cross-contamination|Buy from hygienic sources|Check food expiry dates",
                 "Seek medical care for bloody diarrhea, high fever, or severe dehydration. Most cases resolve in 1-3 days.",
                 "Bloody diarrhea|Signs of severe dehydration (no urination, sunken eyes)|High fever (103°F+)|Neurological symptoms|Diarrhea in infants"},

                {"Kidney Stones",
                 "Hard mineral and salt deposits that form inside the kidneys and can affect the urinary tract.",
                 "Severe sharp back or side pain (flank pain)|Pain radiating to lower abdomen and groin|Painful or burning urination|Pink, red, or brown urine|Nausea|Frequent urination",
                 "Dehydration and insufficient water intake|High sodium/oxalate diet|Family history|Obesity|Hyperparathyroidism|Certain medications",
                 "Pain relievers: Ibuprofen, Ketorolac (doctor prescribed)|Alpha-blockers (Tamsulosin - helps pass stones)|Lithotripsy (shock wave treatment)|Surgical removal (large stones)",
                 "Drink 2.5-3 liters of water daily|Reduce excess salt and sodium intake|Limit high-oxalate foods (spinach, nuts)|Maintain healthy weight|Limit animal protein",
                 "Stay well-hydrated (main prevention)|Low-sodium diet|Limit animal protein|Adequate calcium intake|Citrus foods (lemon juice reduces stone formation)",
                 "Urologist consultation for stones larger than 5mm. CT scan needed for diagnosis. Urine analysis recommended.",
                 "Unbearable pain with vomiting|Fever with kidney pain (infection)|Blood in urine|Complete urinary blockage|Signs of kidney infection"},

                {"Depression",
                 "A serious mood disorder affecting thoughts, feelings, and daily activities. A medical condition, not a weakness.",
                 "Persistent sadness or hopelessness|Loss of interest in activities|Sleep disturbances (insomnia or oversleeping)|Fatigue and low energy|Changes in appetite|Difficulty concentrating|Thoughts of self-harm",
                 "Chemical imbalance (serotonin, dopamine)|Trauma or life events|Genetic factors|Chronic illness|Social isolation|Hormonal changes",
                 "SSRIs: Fluoxetine, Sertraline (doctor prescribed antidepressants)|SNRIs: Venlafaxine|Psychotherapy (CBT)|Combination therapy|Never self-medicate",
                 "Seek professional psychological help immediately|Exercise regularly (proven mood booster)|Maintain social connections|Regular sleep schedule|Avoid alcohol and drugs",
                 "Build strong social support network|Regular physical activity|Mindfulness and meditation|Identify early warning signs|Therapy and counseling|Stress management",
                 "Always consult psychiatrist or psychologist. Never adjust or stop medications without guidance. Regular therapy sessions essential.",
                 "Thoughts of suicide or self-harm - CALL EMERGENCY|Complete inability to function|Psychosis symptoms|Severe self-neglect"},

                {"Anxiety Disorder",
                 "A mental health condition characterized by excessive, persistent worry and fear that interferes with daily activities.",
                 "Excessive, uncontrollable worry|Rapid heartbeat (palpitations)|Restlessness and feeling on edge|Shortness of breath|Sweating and trembling|Difficulty sleeping|Avoidance behavior",
                 "Genetic predisposition|Brain chemistry imbalances|Traumatic life experiences|Chronic stress|Medical conditions|Caffeine and substance use",
                 "Anti-anxiety: Buspirone (doctor prescribed)|Benzodiazepines (short-term, doctor prescribed)|SSRIs/SNRIs (long-term management)|Beta-blockers (physical symptoms)|CBT therapy",
                 "Practice deep breathing and meditation|Regular physical exercise|Limit caffeine and alcohol|CBT therapy (highly effective)|Journaling|Adequate sleep|Social support",
                 "Regular mindfulness practice|Stress management techniques|Therapy before anxiety becomes severe|Healthy lifestyle|Limit news and social media|Build resilience",
                 "Seek psychiatrist or psychologist evaluation. CBT is highly effective. Medication management requires regular follow-ups.",
                 "Panic attack with chest pain (rule out cardiac)|Agoraphobia preventing daily function|Suicidal thoughts|Complete inability to function at work/home"},

                {"Obesity",
                 "A complex chronic disease involving excessive body fat accumulation that increases the risk of other health conditions.",
                 "Excess body weight (BMI > 30)|Fatigue and low energy|Joint pain especially knees and back|Breathing difficulties|Sleep apnea|Excessive sweating|Low self-esteem",
                 "Caloric excess over expenditure|Sedentary lifestyle|Genetic factors|Hormonal disorders (thyroid, PCOS)|Psychological eating|Certain medications|Sleep deprivation",
                 "Weight-management medications under medical supervision|Orlistat (fat absorption blocker)|GLP-1 agonists: Semaglutide (doctor prescribed)|Bariatric surgery (severe cases)|Behavioral therapy",
                 "Follow balanced calorie-controlled diet|Regular physical exercise (150 min/week minimum)|Monitor weight weekly|Avoid processed and junk foods|Behavioral counseling|Track food intake",
                 "Prevent childhood obesity|Physical education and activity|Reduce screen time|Cook healthy meals at home|Limit sugary drinks|Regular weight monitoring",
                 "Consult endocrinologist and nutritionist. Structured medical weight management program. Regular monitoring for comorbidities.",
                 "BMI above 40 with health complications|Sleep apnea requiring intervention|Obesity-related heart disease|Uncontrolled diabetes from obesity"}
            };

            for (Object[] row : data) {
                ps.setString(1, (String)row[0]);
                ps.setString(2, (String)row[1]);
                ps.setString(3, (String)row[2]);
                ps.setString(4, (String)row[3]);
                ps.setString(5, (String)row[4]);
                ps.setString(6, (String)row[5]);
                ps.setString(7, (String)row[6]);
                ps.setString(8, (String)row[7]);
                ps.setString(9, (String)row[8]);
                ps.executeUpdate();
            }
            System.out.println("[DB] Disease data seeded: " + data.length + " diseases.");
        }
    }
}
