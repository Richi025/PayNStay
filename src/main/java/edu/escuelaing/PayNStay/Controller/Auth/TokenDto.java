package edu.escuelaing.PayNStay.Controller.Auth;


import java.util.Date;

import java.util.Date;

public record TokenDto(
        String token,
        Date expirationDate) {

}
