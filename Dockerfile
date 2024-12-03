# استخدم صورة JDK 17 الرسمية من Eclipse Temurin
FROM eclipse-temurin:17-jdk-alpine

# تحديد المجلد الذي سيتم نسخ التطبيق فيه داخل الحاوية
VOLUME /tmp

# نسخ ملف JAR الذي تم إنشاؤه من المشروع إلى داخل الحاوية
COPY target/School_Management_System-0.0.1-SNAPSHOT.jar app.jar

# تعيين نقطة الدخول للحاوية لتشغيل التطبيق
ENTRYPOINT ["java", "-jar", "/app.jar"]
