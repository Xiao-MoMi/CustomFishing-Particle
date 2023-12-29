plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "net.momirealms"
version = "1.0"

repositories {
    mavenCentral()
    maven("https://maven.aliyun.com/repository/public/")
    maven("https://papermc.io/repo/repository/maven-public/")
    maven("https://jitpack.io/")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.20.4-R0.1-SNAPSHOT")
    compileOnly("com.github.Xiao-MoMi:Custom-Fishing:2.0.6")
    implementation("com.github.602723113:ParticleLib:1.5.1")
}

tasks {
    shadowJar {
        relocate ("com.github.602723113", "net.momirealms.customfishing.expansion")
    }
}

tasks.named("build").get().dependsOn("shadowJar").doLast {
    println("Deleting: "+ "build/libs/"+project.name+"-"+project.version+".jar")
}