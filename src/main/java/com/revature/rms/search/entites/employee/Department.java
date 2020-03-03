package com.revature.rms.search.entites.employee;

public enum Department {

    TRAINING("Training"), STAGING("Dev"), QC("Qc"), RETENTION("Retention"), HR("Hr");

    private String department;

    Department(String dept){
        this.department = dept;
    }

    public static Department getByDept(String dept){
        for (Department dep : Department.values()) {
            if (dep.department == dept) {
                return dep;
            }
        }
        return TRAINING;
    }

    @Override
    public String toString(){
        return this.department;
    }
}
