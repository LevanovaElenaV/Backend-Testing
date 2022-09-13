package lesson4;

public class User {
    private String name;
    private String surname;
    private String address;
    private String email;

    private User(Builder builder) {
        name = builder.name;
        surname = builder.surname;
        address = builder.address;
        email = builder.email;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String name;          // поля те же
        private String surname;
        private String address;
        private String email;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setSurname(String surname) {
            this.surname = surname;
            return this;
        }

        public Builder setAddress(String address) {
            this.address = address;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
