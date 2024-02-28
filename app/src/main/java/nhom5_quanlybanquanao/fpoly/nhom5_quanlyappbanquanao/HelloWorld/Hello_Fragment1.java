package nhom5_quanlybanquanao.fpoly.nhom5_quanlyappbanquanao.HelloWorld;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import nhom5_quanlybanquanao.fpoly.nhom5_quanlyappbanquanao.R;

public class Hello_Fragment1 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hello_1, container, false);
        return view;
    }
}