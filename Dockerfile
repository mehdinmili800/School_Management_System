# استخدم صورة JDK 17 الرسمية
FROM eclipse-temurin:17-jdk-alpine

# نسخ ملف JAR إلى داخل الحاوية
COPY ./target/School_Management_System-0.0.1-SNAPSHOT.jar app.jar

# تعيين نقطة الدخول للحاوية لتشغيل التطبيق
ENTRYPOINT ["java", "-jar", "/app.jar"]
