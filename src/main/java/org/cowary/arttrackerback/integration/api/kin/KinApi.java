package org.cowary.arttrackerback.integration.api.kin;

public class KinApi {

    public static FilmApi filmApi() {
        return new FilmApi();
    }

    public static SerialApi serialApi() {
        return new SerialApi();
    }
}
