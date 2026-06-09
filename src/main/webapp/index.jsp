<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <jsp:include page="/WEB-INF/views/includes/header.jsp" />

        <!-- Hero Section -->
        <section class="hero">
            <div class="hero-bg-anim"></div>
            <div class="hero-content">
                <div class="hero-badge"><i class="fas fa-shield-alt"></i> Trusted Health Assistant</div>
                <h1 class="hero-title">Health Chatbot<br><span class="gradient-text">Healthcare Companion</span></h1>
                <p class="hero-subtitle">Get instant disease information, symptom analysis, medicine guidance, and book
                    appointments — all in one place.</p>
                <div class="hero-actions">
                    <a href="#chatbot" class="btn-primary"><i class="fas fa-robot"></i> Start Chatting</a>
                    <a href="${pageContext.request.contextPath}/disease" class="btn-outline"><i
                            class="fas fa-virus"></i> Browse Diseases</a>
                </div>
                <div class="hero-stats">
                    <div class="stat"><span class="stat-num">20+</span><span class="stat-label">Diseases</span></div>
                    <div class="stat"><span class="stat-num">24/7</span><span class="stat-label">Available</span></div>
                    <div class="stat"><span class="stat-num">100%</span><span class="stat-label">Free</span></div>
                </div>
            </div>
            <div class="hero-visual">
                <div class="floating-card card1"><i class="fas fa-heartbeat"></i><span>Health Monitor</span></div>
                <div class="floating-card card2"><i class="fas fa-pills"></i><span>Medicine Guide</span></div>
                <div class="floating-card card3"><i class="fas fa-stethoscope"></i><span>Symptom Check</span></div>
                <div class="hero-circle"></div>
            </div>
        </section>

        <!-- Feature Cards -->
        <section class="features-section">
            <div class="section-header">
                <h2>Everything You Need for <span class="gradient-text">Better Health</span></h2>
                <p>Comprehensive healthcare information at your fingertips</p>
            </div>
            <div class="features-grid">
                <div class="feature-card">
                    <div class="feature-icon" style="background: linear-gradient(135deg,#667eea,#764ba2)"><i
                            class="fas fa-robot"></i></div>
                    <h3>Chatbot</h3>
                    <p>Ask health questions in natural language and get instant, accurate responses powered by medical
                        knowledge.</p>
                    <a href="#chatbot" class="feature-link">Try Now <i class="fas fa-arrow-right"></i></a>
                </div>
                <div class="feature-card">
                    <div class="feature-icon" style="background: linear-gradient(135deg,#f093fb,#f5576c)"><i
                            class="fas fa-search-plus"></i></div>
                    <h3>Symptom Checker</h3>
                    <p>Describe your symptoms and get possible disease matches with precautions and when to see a
                        doctor.</p>
                    <a href="${pageContext.request.contextPath}/search" class="feature-link">Check Symptoms <i
                            class="fas fa-arrow-right"></i></a>
                </div>
                <div class="feature-card">
                    <div class="feature-icon" style="background: linear-gradient(135deg,#4facfe,#00f2fe)"><i
                            class="fas fa-pills"></i></div>
                    <h3>Medicine Guide</h3>
                    <p>General OTC medicine recommendations with dosage guidance and important precautions.</p>
                    <a href="${pageContext.request.contextPath}/disease" class="feature-link">View Medicines <i
                            class="fas fa-arrow-right"></i></a>
                </div>
                <div class="feature-card">
                    <div class="feature-icon" style="background: linear-gradient(135deg,#43e97b,#38f9d7)"><i
                            class="fas fa-calendar-check"></i></div>
                    <h3>Book Appointment</h3>
                    <p>Schedule appointments with specialized doctors across all major medical departments.</p>
                    <a href="${pageContext.request.contextPath}/appointment" class="feature-link">Book Now <i
                            class="fas fa-arrow-right"></i></a>
                </div>
                <div class="feature-card">
                    <div class="feature-icon" style="background: linear-gradient(135deg,#fa709a,#fee140)"><i
                            class="fas fa-shield-virus"></i></div>
                    <h3>Prevention Tips</h3>
                    <p>Evidence-based prevention strategies and health precautions for 20+ common diseases.</p>
                    <a href="${pageContext.request.contextPath}/disease" class="feature-link">Learn More <i
                            class="fas fa-arrow-right"></i></a>
                </div>
                <div class="feature-card">
                    <div class="feature-icon" style="background: linear-gradient(135deg,#ff9a9e,#fecfef)"><i
                            class="fas fa-ambulance"></i></div>
                    <h3>Emergency Guide</h3>
                    <p>Instant emergency guidance with critical warning signs and emergency contact numbers.</p>
                    <a href="#chatbot" class="feature-link">Get Help <i class="fas fa-arrow-right"></i></a>
                </div>
            </div>
        </section>

        <!-- Disease Quick Access -->
        <section class="disease-section">
            <div class="section-header">
                <h2>Common <span class="gradient-text">Diseases</span> Covered</h2>
                <p>Comprehensive information for 20+ diseases with symptoms, medicines, and prevention</p>
            </div>
            <div class="disease-tags">
                <a href="${pageContext.request.contextPath}/search?q=Common+Cold" class="disease-tag cold"><i
                        class="fas fa-snowflake"></i> Common Cold</a>
                <a href="${pageContext.request.contextPath}/search?q=Influenza" class="disease-tag flu"><i
                        class="fas fa-thermometer-half"></i> Influenza</a>
                <a href="${pageContext.request.contextPath}/search?q=COVID" class="disease-tag covid"><i
                        class="fas fa-virus"></i> COVID-19</a>
                <a href="${pageContext.request.contextPath}/search?q=Diabetes" class="disease-tag diabetes"><i
                        class="fas fa-tint"></i> Diabetes</a>
                <a href="${pageContext.request.contextPath}/search?q=Hypertension" class="disease-tag bp"><i
                        class="fas fa-heart-pulse"></i> Hypertension</a>
                <a href="${pageContext.request.contextPath}/search?q=Asthma" class="disease-tag asthma"><i
                        class="fas fa-lungs"></i> Asthma</a>
                <a href="${pageContext.request.contextPath}/search?q=Migraine" class="disease-tag migraine"><i
                        class="fas fa-brain"></i> Migraine</a>
                <a href="${pageContext.request.contextPath}/search?q=Dengue" class="disease-tag dengue"><i
                        class="fas fa-bug"></i> Dengue</a>
                <a href="${pageContext.request.contextPath}/search?q=Malaria" class="disease-tag malaria"><i
                        class="fas fa-mosquito"></i> Malaria</a>
                <a href="${pageContext.request.contextPath}/search?q=Typhoid" class="disease-tag typhoid"><i
                        class="fas fa-bacteria"></i> Typhoid</a>
                <a href="${pageContext.request.contextPath}/search?q=Anxiety" class="disease-tag anxiety"><i
                        class="fas fa-brain"></i> Anxiety</a>
                <a href="${pageContext.request.contextPath}/search?q=Depression" class="disease-tag depression"><i
                        class="fas fa-cloud-rain"></i> Depression</a>
                <a href="${pageContext.request.contextPath}/search?q=Arthritis" class="disease-tag arthritis"><i
                        class="fas fa-bone"></i> Arthritis</a>
                <a href="${pageContext.request.contextPath}/search?q=Anemia" class="disease-tag anemia"><i
                        class="fas fa-droplet"></i> Anemia</a>
                <a href="${pageContext.request.contextPath}/search?q=Obesity" class="disease-tag obesity"><i
                        class="fas fa-weight-scale"></i> Obesity</a>
                <a href="${pageContext.request.contextPath}/disease" class="disease-tag all"><i class="fas fa-plus"></i>
                    View All</a>
            </div>
        </section>

        <!-- Chatbot Section -->
        <section class="chatbot-section" id="chatbot">
            <div class="section-header">
                <h2>Chat with <span class="gradient-text">HealthCare</span></h2>
                <p>Ask anything about diseases, symptoms, medicines, or book an appointment</p>
            </div>
            <div class="chat-container">
                <div class="chat-header">
                    <div class="chat-avatar"><i class="fas fa-robot"></i><span class="status-dot"></span></div>
                    <div class="chat-info">
                        <h3>HealthCare Assistant</h3>
                        <span class="status-text">Online • Ready to help</span>
                    </div>
                    <button class="clear-chat-btn" onclick="clearChat()" title="Clear Chat"><i
                            class="fas fa-trash-alt"></i></button>
                </div>
                <div class="chat-messages" id="chatMessages">
                    <div class="message bot-message">
                        <div class="msg-avatar"><i class="fas fa-robot"></i></div>
                        <div class="msg-content">
                            <div class="msg-bubble">
                                👋 <strong>Hello! I'm your HealthCare Assistant.</strong><br><br>
                                I can help you with:<br>
                                🦠 Disease information<br>
                                🤧 Symptom analysis<br>
                                💊 Medicine guidance<br>
                                📅 Appointment booking<br>
                                🚨 Emergency guidance<br><br>
                                <em>How can I assist you today?</em>
                            </div>
                            <span class="msg-time">Just now</span>
                        </div>
                    </div>
                </div>
                <div class="quick-replies" id="quickReplies">
                    <button class="quick-btn" onclick="sendQuick('I have fever and cough')">🤧 Fever & Cough</button>
                    <button class="quick-btn" onclick="sendQuick('Tell me about Diabetes')">🩺 Diabetes Info</button>
                    <button class="quick-btn" onclick="sendQuick('Book an appointment')">📅 Book Appointment</button>
                    <button class="quick-btn" onclick="sendQuick('Emergency help')">🚨 Emergency</button>
                    <button class="quick-btn" onclick="sendQuick('Health reminders')">💡 Health Tips</button>
                    <button class="quick-btn" onclick="sendQuick('Contact hospital')">🏥 Contact Hospital</button>
                </div>
                <div class="chat-input-area">
                    <div class="input-wrapper">
                        <input type="text" id="userInput" class="chat-input"
                            placeholder="Ask about symptoms, diseases, medicines..."
                            onkeydown="if(event.key==='Enter') sendMessage()" maxlength="500" autocomplete="off">
                        <button class="send-btn" onclick="sendMessage()" id="sendBtn">
                            <i class="fas fa-paper-plane"></i>
                        </button>
                    </div>
                    <p class="input-disclaimer">⚕️ For informational purposes only. Consult a doctor for medical advice.
                    </p>
                </div>
            </div>
        </section>

        <!-- Emergency Banner -->
        <section class="emergency-section">
            <div class="emergency-content">
                <div class="emergency-icon"><i class="fas fa-ambulance"></i></div>
                <div class="emergency-text">
                    <h2>🚨 Medical Emergency?</h2>
                    <p>Don't wait! Call emergency services immediately for life-threatening situations.</p>
                </div>
                <div class="emergency-numbers">
                    <a href="tel:108" class="emergency-call"><i class="fas fa-phone"></i>
                        108<br><small>Ambulance</small></a>
                    <a href="tel:112" class="emergency-call"><i class="fas fa-phone"></i>
                        112<br><small>National</small></a>
                    <a href="tel:102" class="emergency-call"><i class="fas fa-phone"></i>
                        102<br><small>Emergency</small></a>
                </div>
            </div>
        </section>

        <jsp:include page="/WEB-INF/views/includes/footer.jsp" />

        <script>
            const ctx = '${pageContext.request.contextPath}';

            function sendMessage() {
                const input = document.getElementById('userInput');
                const msg = input.value.trim();
                if (!msg) return;
                appendMessage(msg, 'user');
                input.value = '';
                document.getElementById('quickReplies').style.display = 'none';
                showTyping();
                fetch(ctx + '/chat', {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
                    body: 'message=' + encodeURIComponent(msg)
                })
                    .then(r => r.json())
                    .then(data => {
                        removeTyping();
                        appendMessage(data.response, 'bot', data.type);
                    })
                    .catch(() => {
                        removeTyping();
                        appendMessage('Sorry, I encountered an error. Please try again.', 'bot', 'error');
                    });
            }

            function sendQuick(msg) {
                document.getElementById('userInput').value = msg;
                sendMessage();
            }

            function appendMessage(text, sender, type) {
                const container = document.getElementById('chatMessages');
                const div = document.createElement('div');
                div.className = 'message ' + (sender === 'user' ? 'user-message' : 'bot-message');
                if (type === 'emergency') div.classList.add('emergency-msg');
                const time = new Date().toLocaleTimeString('en-US', { hour: '2-digit', minute: '2-digit' });
                const icon = sender === 'user' ? 'fa-user' : 'fa-robot';
                div.innerHTML = `
        <div class="msg-avatar"><i class="fas \${icon}"></i></div>
        <div class="msg-content">
            <div class="msg-bubble">\${formatText(text)}</div>
            <span class="msg-time">\${time}</span>
        </div>`;
                container.appendChild(div);
                container.scrollTop = container.scrollHeight;
            }

            function formatText(text) {
                return text
                    .replace(/\*\*(.*?)\*\*/g, '<strong>$1</strong>')
                    .replace(/\n/g, '<br>')
                    .replace(/•/g, '&bull;')
                    .replace(/⚡/g, '<span style="color:#f59e0b">⚡</span>');
            }

            function showTyping() {
                const container = document.getElementById('chatMessages');
                const div = document.createElement('div');
                div.className = 'message bot-message typing-indicator-msg';
                div.id = 'typingIndicator';
                div.innerHTML = `<div class="msg-avatar"><i class="fas fa-robot"></i></div>
        <div class="msg-content"><div class="msg-bubble typing-bubble">
        <span></span><span></span><span></span></div></div>`;
                container.appendChild(div);
                container.scrollTop = container.scrollHeight;
            }

            function removeTyping() {
                const el = document.getElementById('typingIndicator');
                if (el) el.remove();
            }

            function clearChat() {
                const container = document.getElementById('chatMessages');
                container.innerHTML = '';
                document.getElementById('quickReplies').style.display = 'flex';
                appendMessage('Chat cleared. How can I help you?', 'bot', 'info');
            }
        </script>