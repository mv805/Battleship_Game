class User {
    private String firstName;
    private String lastName;

    public User() {
        this.firstName = "";
        this.lastName = "";
    }

    public void setFirstName(String firstName) {
        if (firstName != null){
            this.firstName = firstName;
        }
    }

    public void setLastName(String lastName) {
        if (lastName != null){
            this.lastName = lastName;
        }
    }

    public String getFullName() {
        if (lastName == "" && firstName == ""){
            return "Unknown";
        }
        else if (lastName == "" && firstName != ""){
            return firstName;
        }
        else if (lastName != "" && firstName == ""){
            return lastName;
        }
        else {
            return firstName + " " + lastName;
        }
    }
}
