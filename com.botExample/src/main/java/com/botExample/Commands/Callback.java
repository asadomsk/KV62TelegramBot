package com.botExample.Commands;

//import com.pengrad.telegrambot.request.BaseRequest;
//import com.pengrad.telegrambot.response.BaseResponse;

import java.io.IOException;

/**
 * stas
 * 5/3/16.
 */
public interface Callback{

    void onResponse();

    void onFailure();
}