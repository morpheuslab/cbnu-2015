package com.example.boundservice;

import java.util.Random;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class LocalService extends Service {

	// 클라이언트에게 반환되는 바인더
    private final IBinder mBinder = new LocalBinder();
    // 난수 발생기
    private final Random mGenerator = new Random();

    // 클라이언트 바인더를 위한 클래스 
    public class LocalBinder extends Binder {
        LocalService getService() {
            return LocalService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    // 클라이언트를 위한 메소드 
    public int getRandomNumber() {
      return mGenerator.nextInt(100);
    }
}