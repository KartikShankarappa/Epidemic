package com.kartik.epidemic;

public class Population {

    // will be created by first running - java com.kartik.epidemic.SpreadEpidemic


    @Infection(cause = Disease.SmallPox) public String getInfectedSmallPox() {
        return "1b308f43-fa86-4510-863f-a4d15ecb8ea4b4d55057-abd0-4944-8696-2b3dc6b667001001eec24eb7-fe9a-4235-9007-d7603d4da915";
    }

    @Infection(cause = Disease.Ebola) public String getInfectedEbola() {
        return "0682ac39-3e9c-4945-a3a8-8b202a534fd90111d56af6bb-ca62-4eaf-a118-748c0e7d6d8c1e68780f-59e7-4a34-b069-cfb1eb71b65f";
    }

    @Infection(cause = Disease.Sars) public String getInfectedSars() {
        return "a34b290f-c45d-42c6-83ea-ccb5794ea85011114a828178-7a66-4a6c-9650-bc070353c1079369209a-276e-4e14-9d29-607965818fd4";
    }

    @Infection(cause = Disease.H1N1) public String getInfectedH1N1() {
        return "0110100000011110100033fde8f9-ebf2-4ad8-8095-02d2c3d79f42ee01f1b1-a966-4431-b620-9c0c9254770a838d1564-82ab-420d-81a9-ecbb9bf3a1c7";
    }


}
