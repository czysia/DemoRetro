package com.example.mob403demo3retro;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.app_ban_hang_tot_nghiep.databinding.ActivityLoginBinding;
import com.example.app_ban_hang_tot_nghiep.fragment.LoginFragment;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        gotoLoginFragment();
    }


    public void gotoLoginFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.containerLogin, new LoginFragment()).commit();
    }
}