package nhom5_quanlybanquanao.fpoly.nhom5_quanlyappbanquanao.KH_Loader;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import nhom5_quanlybanquanao.fpoly.nhom5_quanlyappbanquanao.DAO.NhanVienDAO;
import nhom5_quanlybanquanao.fpoly.nhom5_quanlyappbanquanao.DAO.ThongBaoDAO;
import nhom5_quanlybanquanao.fpoly.nhom5_quanlyappbanquanao.Entity.NhanVien;
import nhom5_quanlybanquanao.fpoly.nhom5_quanlyappbanquanao.Entity.ThongBao;
import nhom5_quanlybanquanao.fpoly.nhom5_quanlyappbanquanao.KH_Adapter.All_ThongBao_Adapter;

public class All_ThongBao_Loader extends AsyncTask<String, Void, ArrayList<ThongBao>> {
    @SuppressLint("StaticFieldLeak")
    Context context;
    ThongBaoDAO thongBaoDAO;
    String TAG = "NV_DonHang_Loader_____";
    @SuppressLint("StaticFieldLeak")
    RecyclerView reView;
    @SuppressLint("StaticFieldLeak")
    LinearLayout loadingView, emptyLayout;
    String table;

    public All_ThongBao_Loader(Context context, RecyclerView reView, LinearLayout loadingView, LinearLayout emptyLayout, String table) {
        this.context = context;
        this.reView = reView;
        this.loadingView = loadingView;
        this.table = table;
        this.emptyLayout = emptyLayout;
    }

    @Override
    protected ArrayList<ThongBao> doInBackground(String... strings) {
        thongBaoDAO = new ThongBaoDAO(context);

        String id = strings[0];

        if (table.equals("kh")) {
            return thongBaoDAO.selectThongBao(null, "maKH=?", new String[]{id}, "ngayTB DESC", table);
        } else if (table.equals("nv")) {
            NhanVienDAO nhanVienDAO = new NhanVienDAO(context);
            ArrayList<NhanVien> getList = nhanVienDAO.selectNhanVien(null, "maNV=?", new String[]{id}, null);
            NhanVien getNV = getList.get(0);
            if (getNV != null){
                if (getNV.getRoleNV().equals("Bán hàng Online")){
                    return thongBaoDAO.selectThongBao(null, "maNV=? or maNV='Online'", new String[]{id}, "ngayTB DESC", table);
                } else {
                    return thongBaoDAO.selectThongBao(null, "maNV=?", new String[]{id}, "ngayTB DESC", table);
                }
            }
        } else {
            return thongBaoDAO.selectThongBao(null, "admin=Admin", null, "ngayTB DESC", table);
        }
        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<ThongBao> listTB) {
        super.onPostExecute(listTB);

        if (reView != null && emptyLayout != null && loadingView != null) {
            if (listTB.size() > 0){
                loadingView.setVisibility(View.GONE);
                emptyLayout.setVisibility(View.GONE);
                setupReView(listTB, reView);
            } else {
                loadingView.setVisibility(View.GONE);
                emptyLayout.setVisibility(View.VISIBLE);
            }
        }
    }

    private void setupReView(ArrayList<ThongBao> listTB, RecyclerView recyclerView) {
        Log.d(TAG, "setUpReView: true");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);
        All_ThongBao_Adapter all_thongBao_adapter = new All_ThongBao_Adapter(listTB, context);
        recyclerView.setAdapter(all_thongBao_adapter);
    }

}
