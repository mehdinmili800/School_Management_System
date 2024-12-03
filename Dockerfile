# استخدام صورة JDK 17 الأساسية
FROM eclipse-temurin:17-jdk-alpine

# إعداد متغير البيئة لتحديد المنطقة الزمنية (اختياري)
ENV TZ=UTC

# نسخ ملف JAR المترجم من المجلد target إلى داخل الحاوية
COPY target/School_Management_System-0.0.1-SNAPSHOT.jar app.jar

# تحديد نقطة الدخول للحاوية وتشغيل التطبيق
ENTRYPOINT ["java", "-jar", "/app.jar"]
