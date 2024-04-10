package com.rogih.propostabackend.config;

import lombok.extern.log4j.Log4j2;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;

import java.io.FileReader;

@Log4j2
public class AppVersion {

    public static String getAppVersion() {
        try {
            MavenXpp3Reader reader = new MavenXpp3Reader();
            Model model = reader.read(new FileReader("pom.xml"));
            String appVersion = model.getVersion();
            return appVersion;
        } catch (Exception e) {
            log.error("Erro ao extrair a versão da aplicação: " + e.getMessage());
            return null;
        }
    }
}
