package com.kartik.epidemic;

public class Test {

    public String getSmallPoxCure() {
        String idAsBinary = getIdAsBinary();
        return idAsBinary.substring(0, 4);
    }


    public String getEbolaCure() {
        String idAsBinary = getIdAsBinary();
        return idAsBinary.substring(4, 8);
    }

    @Vaccine(cures = Disease.Sars)
    public String getSarsCure() {
        String idAsBinary = getIdAsBinary();
        return idAsBinary.substring(8, 12);
    }

    @Vaccine(cures = Disease.H1N1)
    public String getH1N1Cure() {
        String idAsBinary = getIdAsBinary();
        return idAsBinary.substring(12);
    }
    private String getIdAsBinary() {
        return String.format("%16s", Long.toBinaryString(id)).replace(' ', '0');
    }
    
    private final long id = 2549514728l;
    public static void main(String[] args)
    {
    	Test a = new Test();
    	System.out.println(a.getH1N1Cure());
    }

}
