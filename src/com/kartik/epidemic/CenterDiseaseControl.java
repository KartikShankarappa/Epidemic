package com.kartik.epidemic;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CenterDiseaseControl {

    public static void main(String[] args) {
        if ((args == null) || (args.length != 1)) {
            throw new AssertionError("Please specify your NetID");
        }
        String netId = args[0];
        CenterDiseaseControl centerDiseaseControl = new CenterDiseaseControl(netId);
        Population population = new Population();
        try {
			if (centerDiseaseControl.areDiseasesCured(population)) {
			    System.out.printf("Congrats! You cured the epidemic!");
			} else {
			    System.out.printf("Sadly, the epidemic still spreads...");
			}
		} catch (IllegalAccessException e) {
			System.out.format("An error has occured: %n%s%n", e.getMessage());
		} catch (IllegalArgumentException e) {
			System.out.format("An error has occured: %n%s%n", e.getMessage());
		} catch (InvocationTargetException e) {
			System.out.format("An error has occured: %n%s%n", e.getMessage());
		}
    }

    private final long id;

    public CenterDiseaseControl(String id) {
        this.id = CRC32s.crc32(id);
    }

    public boolean areDiseasesCured(Population population) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Map<Disease, String> infections = getInfections(population);
        Map<Disease, String> cures = getCures();
        if (infections.isEmpty() || cures.isEmpty()) {
            throw new IllegalStateException("Not properly setup");
        }
        for (String value : infections.values()) {
            if ("".equals(value)) {
                throw new IllegalStateException("Not properly setup");
            }
        }
        for (Disease disease : Disease.values()) {
            String infection = infections.get(disease);
            String cure = cures.get(disease);
            if ((infection == null) || (cure == null)) {
                throw new IllegalStateException("Infection or cure not found.");
            }
            Pattern pattern = Pattern.compile(String.format(".*%s.*", cure));
            Matcher matcher = pattern.matcher(infection);
            if (!matcher.find()) {
                return false;
            }
        }
        return true;
    }
    
    // I have hardcoded the seed and antidote values with respect to my netid and the four methods
    
    @Vaccine(cures = Disease.SmallPox, seed = 2549514728l, antidote = "1001")
    public String getSmallPoxCure() {
        String idAsBinary = getIdAsBinary();
        return idAsBinary.substring(0, 4);
    }

    @Vaccine(cures = Disease.Ebola, seed = 2549514728l, antidote = "0111")
    public String getEbolaCure() {
        String idAsBinary = getIdAsBinary();
        return idAsBinary.substring(4, 8);
    }

    @Vaccine(cures = Disease.Sars, seed = 2549514728l, antidote = "1111" )
    public String getSarsCure() {
        String idAsBinary = getIdAsBinary();
        return idAsBinary.substring(8, 12);
    }

    @Vaccine(cures = Disease.H1N1, seed = 2549514728l, antidote = "01101000000111101000")
    public String getH1N1Cure() {
        String idAsBinary = getIdAsBinary();
        return idAsBinary.substring(12);
    }

    private Map<Disease, String> getInfections(Population population) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Method[] methods = Population.class.getDeclaredMethods();
        Map<Disease, String> diseases = new HashMap<>();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Infection.class)) {
                Infection infection = method.getAnnotation(Infection.class);
                Disease disease = infection.cause();
                String result = (String) method.invoke(population);
                if (!"".equals(result)) {
                    diseases.put(disease, result);
                }
            }
        }
        return diseases;
    }

    private Map<Disease, String> getCures() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Method[] centerDiseaseControlMethods = CenterDiseaseControl.class.getDeclaredMethods();
        Map<Disease, String> cures = new HashMap<>(Disease.values().length);
        for (Method method : centerDiseaseControlMethods) {
            if (method.isAnnotationPresent(Vaccine.class)) {
                Vaccine vaccine = method.getAnnotation(Vaccine.class);
                Disease disease = vaccine.cures();
                String result = (String) method.invoke(this);
                if (!"".equals(result) && (vaccine.seed() == id) && (vaccine.antidote().equals(result))) {
                    cures.put(disease, result);
                }
            }
        }
        return cures;
    }

    private String getIdAsBinary() {
        return String.format("%16s", Long.toBinaryString(id)).replace(' ', '0');
    }

}
