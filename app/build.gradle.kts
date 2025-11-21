plugins {
    // Aplica o plugin de 'application' para rodar a CLI
    application
}

repositories {
    // Usa o Maven Central para baixar as bibliotecas
    mavenCentral()
}

dependencies {
    // Biblioteca de Testes (JUnit 5)
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.3")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

application {
    // Define a classe principal da aplicação
    mainClass = "br.com.pixeldex.application.Main"
}

tasks.named<Test>("test") {
    // Habilita suporte ao JUnit 5
    useJUnitPlatform()
}

// Permite digitar no terminal (Scanner)
tasks.named<JavaExec>("run") {
    standardInput = System.`in`
}