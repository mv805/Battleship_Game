class Employee {

    protected String name;
    protected String email;
    protected int experience;

    protected Employee(String name, String email, int experience){
        this.name = name;
        this.email = email;
        this.experience = experience;

    }

    protected String getName(){
        return name;
    }
    protected String getEmail(){
        return email;
    }
    protected int getExperience(){
        return experience;
    }
}

class Developer extends Employee {

    protected String mainLanguage;
    protected String[] skills;

    protected Developer(String name, String email, int exp,
    String mainLanguage, String[] skills){
        super(name, email, exp);
        this.mainLanguage = mainLanguage;
        this.skills = skills;

    }

    protected String getMainLanguage(){
        return mainLanguage;
    }
    protected String[] getSkills(){
        return skills;
    }
}

class DataAnalyst extends Employee {

    protected boolean phd;
    protected String[] methods;

    protected DataAnalyst(String name, String email, int exp,
                          boolean phd, String[] methods){
        super(name, email, exp);
        this.phd = phd;
        this.methods = methods;


    }

    protected String[] getMethods(){
        return methods;
    }
    protected boolean isPhd(){
        return phd;
    }
}