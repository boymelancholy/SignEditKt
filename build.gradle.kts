plugins {
    kotlin("jvm") version "1.3.60"
    id("net.minecrell.plugin-yml.nukkit") version "0.3.0"
    id("com.github.johnrengelman.shadow") version "6.0.0"
}

group = "mcbe.boymelancholy"
version = "1.0-SNAPSHOT"

repositories {
    jcenter()
    maven {
        url = uri("https://repo.nukkitx.com/main/")
    }
    maven {
        url = uri("https://raw.github.com/itsu020402/NukkitFormAPI/mvn-repo/")
    }
}

dependencies {
    implementation(kotlin("stdlib"))
    compileOnly("cn.nukkit:nukkit:1.0-SNAPSHOT")
    testCompileOnly("cn.nukkit:nukkit:1.0-SNAPSHOT")

    compileOnly("nukkit.form.api:NukkitFormAPI:2.0.1-SNAPSHOT")
    testCompileOnly("nukkit.form.api:NukkitFormAPI:2.0.1-SNAPSHOT")
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }

}

nukkit {
    name = "SignEditKt"
    main = "mcbe.boymelancholy.signeditkt.SignEditKt"
    api = listOf("1.0.11")
    authors = listOf("boymelancholy")
}