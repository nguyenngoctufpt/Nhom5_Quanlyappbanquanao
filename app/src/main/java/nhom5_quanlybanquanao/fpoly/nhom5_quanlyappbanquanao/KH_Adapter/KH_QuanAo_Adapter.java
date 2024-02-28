package nhom5_quanlybanquanao.fpoly.nhom5_quanlyappbanquanao.KH_Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import nhom5_quanlybanquanao.fpoly.nhom5_quanlyappbanquanao.Activity.Info_QuanAo_Activity;
import nhom5_quanlybanquanao.fpoly.nhom5_quanlyappbanquanao.DAO.QuanAoRateDAO;
import nhom5_quanlybanquanao.fpoly.nhom5_quanlyappbanquanao.Entity.QuanAo;
import nhom5_quanlybanquanao.fpoly.nhom5_quanlyappbanquanao.Entity.QuanAoRate;
import nhom5_quanlybanquanao.fpoly.nhom5_quanlyappbanquanao.R;
import nhom5_quanlybanquanao.fpoly.nhom5_quanlyappbanquanao.Support.ChangeType;

public class KH_QuanAo_Adapter extends RecyclerView.Adapter<KH_QuanAo_Adapter.AuthorViewHolder> {

    Context context;
    ArrayList<QuanAo> listquanAo;
    String TAG = "KH_QuanAo_Adapter_____";

    public KH_QuanAo_Adapter(ArrayList<QuanAo> listquanAo, Context context) {
        this.listquanAo = listquanAo;
        this.context = context;
    }

    @NonNull
    @Override
    public AuthorViewHolder onCreateViewHolder(@NonNull ViewGroup vGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.cardview_kh_quanao, vGroup, false);
        return new AuthorViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AuthorViewHolder author, @SuppressLint("RecyclerView") final int pos) {
        setRow(pos, author);

        author.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Info_QuanAo_Activity.class);
                QuanAo quanAo = listquanAo.get(pos);
                if (quanAo != null) {
                    final Bundle bundle = new Bundle();
                    bundle.putBinder("laptop", quanAo);
                    Log.d(TAG, "onBindViewHolder: Laptop: " + quanAo.toString());
                    intent.putExtras(bundle);
                    intent.putExtra("openFrom", "viewer");
                    context.startActivity(intent);
                } else {
                    Toast.makeText(context, "Load thông tin sản phẩm lỗi!\nXin vui lòng thử lại sau!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listquanAo.size();
    }

    public static class AuthorViewHolder extends RecyclerView.ViewHolder {
        ImageView imgLaptop;
        TextView name, gia;
        RatingBar ratingBar;

        public AuthorViewHolder(@NonNull View itemView) {
            super(itemView);
            imgLaptop = itemView.findViewById(R.id.imageView_QuanAo);
            name = itemView.findViewById(R.id.textView_TenQuanAo);
            gia = itemView.findViewById(R.id.textView_GiaTien);
//            ram = itemView.findViewById(R.id.textView_Ram);
            ratingBar = itemView.findViewById(R.id.ratingBar_DanhGia);
        }
    }

    public void setRow(int pos, @NonNull AuthorViewHolder author) {
        Log.d(TAG, "setRow: " + pos);
        QuanAo quanAo = listquanAo.get(pos);
        Log.d(TAG, "setRow: Laptop: " + quanAo.toString());


        ChangeType changeType = new ChangeType();
        Bitmap anhLap = changeType.byteToBitmap(quanAo.getAnhquanAo());

        author.imgLaptop.setImageBitmap(anhLap);
        author.name.setText(quanAo.getTenQuanAo());
        author.gia.setText(quanAo.getGiaTien());


        QuanAoRateDAO quanAoRateDAO = new QuanAoRateDAO(context);
        ArrayList<QuanAoRate> list = quanAoRateDAO.selectQuanAoRate(null, "maQuanAo=?", new String[]{quanAo.getMaQuanAo()}, null);
        if (list.size() > 0) {
            float rating = 0;
            for (QuanAoRate lapRate : list) {
                rating += lapRate.getRating();
            }
            rating = rating / list.size();
            author.ratingBar.setRating(changeType.getRatingFloat(rating));
        } else {
            author.ratingBar.setRating(0f);
        }
    }
}