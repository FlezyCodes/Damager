plugins {
    id("java")
}

group = "br.com.beta"
version = "1.0-SNAPSHOT"

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

repositories {
    mavenCentral()
}

dependencies {
    val lombok = "org.projectlombok:lombok:1.18.34"

    "implementation"(lombok)
    "annotationProcessor"(lombok)

    compileOnly(files("C:/API/pandaspigot.jar"))

    implementation("org.mongodb:mongodb-driver-sync:5.1.0")

}
tasks.jar {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE

    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it)})
}