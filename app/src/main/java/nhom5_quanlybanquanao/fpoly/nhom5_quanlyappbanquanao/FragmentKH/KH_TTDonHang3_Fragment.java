package nhom5_quanlybanquanao.fpoly.nhom5_quanlyappbanquanao.FragmentKH;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import nhom5_quanlybanquanao.fpoly.nhom5_quanlyappbanquanao.DAO.KhachHangDAO;
import nhom5_quanlybanquanao.fpoly.nhom5_quanlyappbanquanao.Entity.KhachHang;
import nhom5_quanlybanquanao.fpoly.nhom5_quanlyappbanquanao.KH_Loader.KH_DonHang_Loader;
import nhom5_quanlybanquanao.fpoly.nhom5_quanlyappbanquanao.R;


public class KH_TTDonHang3_Fragment extends Fragment {
    Context context;
    String TAG = "KH_DonHang_Activity_____";
    RecyclerView recyclerView;
    LinearLayout linearLayout, linearDonHangEmpty;
    KhachHang khachHang;

    public KH_TTDonHang3_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_k_h__t_t_don_hang1_, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = getContext();
        linearLayout = view.findViewById(R.id.loadingView);
        recyclerView = view.findViewById(R.id.recyclerView_KH_DonHang);
        linearDonHangEmpty = view.findViewById(R.id.linearDonHangEmpty);

        getUser();
        if (khachHang != null) {
            KH_DonHang_Loader kh_donHang_loader = new KH_DonHang_Loader(context, recyclerView, linearLayout, linearDonHangEmpty, "yep","Hoàn thành");
            kh_donHang_loader.execute(khachHang.getMaKH());
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getUser();
        if (khachHang != null) {
            KH_DonHang_Loader kh_donHang_loader = new KH_DonHang_Loader(context, recyclerView, linearLayout, linearDonHangEmpty, "yep","Hoàn thành");
            kh_donHang_loader.execute(khachHang.getMaKH());
        }
    }

    private void getUser(){
        SharedPreferences pref = context.getSharedPreferences("Who_Login", Context.MODE_PRIVATE);
        if (pref == null) {
            khachHang = null;
        } else {
            String user = pref.getString("who", "");
            KhachHangDAO khachHangDAO = new KhachHangDAO(context);
            ArrayList<KhachHang> list = khachHangDAO.selectKhachHang(null, "maKH=?", new String[]{user}, null);
            if (list.size() > 0){
                khachHang = list.get(0);
            }
        }
    }
}