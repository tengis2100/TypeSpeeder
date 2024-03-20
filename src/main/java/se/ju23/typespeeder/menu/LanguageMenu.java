package se.ju23.typespeeder.menu;

import se.ju23.typespeeder.Messagable;

public enum LanguageMenu  implements Messagable {
        ENGLISH("english"),
        SWEDISH("Svenska");


        private String message;

        LanguageMenu(String message) {
            this.message = message;
        }

        @Override
        public String getMassage() {
            return message;
        }
    }

