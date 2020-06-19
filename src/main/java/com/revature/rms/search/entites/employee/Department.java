package com.revature.rms.search.entites.employee;

public enum Department {
  TRAINING("Training"),
  STAGING("Dev"),
  QC("Qc"),
  RETENTION("Retention"),
  HR("Hr"),
  RECRUITMENT("RECRUITMENT"),
  DELIVERY("DELIVERY");

  private String departmentName;

  Department(String dept) {
    this.departmentName = dept;
  }

  public static Department getByDept(String dept) {
    for (Department dep : Department.values()) {
      if (dep.departmentName == dept) {
        return dep;
      }
    }
    return TRAINING;
  }

  @Override
  public String toString() {
    return this.departmentName;
  }
}
