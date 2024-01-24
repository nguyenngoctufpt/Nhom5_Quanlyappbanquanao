package nhom5_quanlybanquanao.fpoly.nhom5_quanlyappbanquanao.CreateWallet;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import nhom5_quanlybanquanao.fpoly.nhom5_quanlyappbanquanao.DAO.KhachHangDAO;
import nhom5_quanlybanquanao.fpoly.nhom5_quanlyappbanquanao.Entity.KhachHang;
import nhom5_quanlybanquanao.fpoly.nhom5_quanlyappbanquanao.R;
import nhom5_quanlybanquanao.fpoly.nhom5_quanlyappbanquanao.Support.ChangeType;

public class Vi_Fragment3 extends Fragment {

    ChangeType changeType = new ChangeType();
    KhachHang khachHang;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vi_3, container, false);
        ImageView ava = view.findViewById(R.id.imageView_Avatar);
        TextView ten = view.findViewById(R.id.textView_TenKH);

        getUser();
        if (khachHang != null) {
            ava.setImageBitmap(changeType.byteToBitmap(khachHang.getAvatar()));
            ten.setText(changeType.fullNameKhachHang(khachHang));
        }
        return view;
    }

    private void getUser() {
        SharedPreferences pref = getContext().getSharedPreferences("Who_Login", MODE_PRIVATE);
        if (pref == null) {
            khachHang = null;
        } else {
            String user = pref.getString("who", "");
            KhachHangDAO khachHangDAO = new KhachHangDAO(getContext());
            ArrayList<KhachHang> list = khachHangDAO.selectKhachHang(null, "maKH=?", new String[]{user}, null);
            if (list.size() > 0) {
                khachHang = list.get(0);
            }
        }
    }
}