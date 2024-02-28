package nhom5_quanlybanquanao.fpoly.nhom5_quanlyappbanquanao.NAV_Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import nhom5_quanlybanquanao.fpoly.nhom5_quanlyappbanquanao.DAO.NhanVienDAO;
import nhom5_quanlybanquanao.fpoly.nhom5_quanlyappbanquanao.Entity.NhanVien;
import nhom5_quanlybanquanao.fpoly.nhom5_quanlyappbanquanao.R;
import nhom5_quanlybanquanao.fpoly.nhom5_quanlyappbanquanao.Support.ChangeType;

public class QL_ThongKe_Adapter extends RecyclerView.Adapter<QL_ThongKe_Adapter.AuthorViewHolder> {

    Context context;
    ArrayList<NhanVien> listNV;
    NhanVienDAO nhanVienDAO;
    String TAG = "QL_KhachHang_Adapter_____";
    ChangeType changeType = new ChangeType();

    public QL_ThongKe_Adapter(ArrayList<NhanVien> listNV, Context context) {
        this.listNV = listNV;
        this.context = context;
        nhanVienDAO = new NhanVienDAO(context);
    }

    @NonNull
    @Override
    public AuthorViewHolder onCreateViewHolder(@NonNull ViewGroup vGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.cardview_nva_thongke, vGroup, false);
        return new AuthorViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AuthorViewHolder author, @SuppressLint("RecyclerView") final int pos) {
        NhanVien nhanVien = setRow(pos, author);

        author.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, nhanVien.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listNV.size();
    }

    public static class AuthorViewHolder extends RecyclerView.ViewHolder {
        TextView stt, name, phone, total, soLuong;

        public AuthorViewHolder(@NonNull View itemView) {
            super(itemView);
            stt = itemView.findViewById(R.id.textView_STT);
            name = itemView.findViewById(R.id.textView_TenNV);
            total = itemView.findViewById(R.id.textView_Total);
            phone = itemView.findViewById(R.id.textView_SDT);
            soLuong = itemView.findViewById(R.id.textView_SoSP_DaBan);
        }
    }

    public NhanVien setRow(int pos, @NonNull AuthorViewHolder author) {
        Log.d(TAG, "setRow: " + pos);
        NhanVien nhanVien = listNV.get(pos);
        Log.d(TAG, "setRow: NhanVien: " + nhanVien.toString());

        author.stt.setText(String.valueOf(pos + 1));
        author.name.setText(changeType.fullNameNhanVien(nhanVien));
        author.total.setText(changeType.stringToStringMoney(nhanVien.getDoanhSo()+"000"));
        author.phone.setText(nhanVien.getPhone());
        author.soLuong.setText("Số sp đã bán: " + nhanVien.getSoSP());
        return nhanVien;
    }

}
