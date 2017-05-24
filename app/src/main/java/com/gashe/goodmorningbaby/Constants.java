package com.gashe.goodmorningbaby;

/**
 * Created by Gashe on 23/5/17.
 */

public class Constants {

    public static final Errors ERROR = new Errors();
    public static final Alerts ALERT = new Alerts();

    public static final class Errors{

        private Errors() {  }
    }



    public static final class Alerts{

        public static final String SAVE_SETTING_SUCCESS = "Ajustes guardados correctamente.";

        private Alerts() {  }
    }

    private Constants() {  }

}
