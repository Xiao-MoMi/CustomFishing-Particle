plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "net.momirealms"
version = "1.1"

repositories {
    mavenCentral()
    maven("https://maven.aliyun.com/repository/public/")
    maven("https://papermc.io/repo/repository/maven-public/")
    maven("https://jitpack.io/")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.20.4-R0.1-SNAPSHOT")
    compileOnly("com.github.Xiao-MoMi:Custom-Fishing:2.2.3")
    implementation("com.github.iqtesterrr:ParticleLib:1.5.4")
}

tasks {
    shadowJar {
        relocate ("top.zoyn.particlelib", "net.momirealms.customfishing.expansion.libraries.particlelib")
    }
}

tasks.named("build").get().dependsOn("shadowJar").doLast {
    println("Deleting: "+ "build/libs/"+project.name+"-"+project.version+".jar")
}