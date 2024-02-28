package nhom5_quanlybanquanao.fpoly.nhom5_quanlyappbanquanao.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import nhom5_quanlybanquanao.fpoly.nhom5_quanlyappbanquanao.Database.QLQuanAoDB;
import nhom5_quanlybanquanao.fpoly.nhom5_quanlyappbanquanao.Entity.HangQuanAo;
import nhom5_quanlybanquanao.fpoly.nhom5_quanlyappbanquanao.Support.ChangeType;

public class LoaiQuanAoDAO {
    QLQuanAoDB qlQuanAoDB;
    SQLiteDatabase db;
    Context context;
    String TAG = "LoaiQuanAoDAO_____";
    ChangeType changeType = new ChangeType();

    public LoaiQuanAoDAO(Context context) {
        this.context = context;
        qlQuanAoDB = new QLQuanAoDB(context);
        db = qlQuanAoDB.getWritableDatabase();
    }

    public ArrayList selectHangQuanAo(String[] columns, String selection, String[] selectionArgs, String orderBy) {
        ArrayList<HangQuanAo> listHLP = new ArrayList<>();
        qlQuanAoDB = new QLQuanAoDB(context);
        db = qlQuanAoDB.getWritableDatabase();
        Cursor c = db.query("LoaiQuanAo", columns, selection, selectionArgs, null, null, orderBy);
        Log.d(TAG, "selectLoaiQuanAo: Cursor: " + c.toString());

        if (c.getCount() > 0) {
            Log.d(TAG, "selectLoaiQuanAo: Cursor not null");
            c.moveToFirst();
            while (!c.isAfterLast()) {
                Log.d(TAG, "selectLoaiQuanAo: Cursor not last");
                String maHangLap = c.getString(0);
                byte[] anhHang = c.getBlob(1);
                String tenHangLap = c.getString(2);
                HangQuanAo newHangQuanAo = new HangQuanAo(maHangLap, tenHangLap, anhHang);
                Log.d(TAG, "selectLoaiQuanAo: new LoaiQuanAo: " + newHangQuanAo.toString());

                listHLP.add(newHangQuanAo);
                c.moveToNext();
            }
            c.close();
        } else {
            Log.d(TAG, "selectLoaiQuanAo: Cursor null");
        }
        db.close();

        return listHLP;
    }

    public void insertHangQuanAo(HangQuanAo hangQuanAo) {
        qlQuanAoDB = new QLQuanAoDB(context);
        db = qlQuanAoDB.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("maLoaiQuanAo", hangQuanAo.getMaHangLap());
        values.put("anhHang", hangQuanAo.getAnhHang());
        values.put("tenLoaiQuanAo", hangQuanAo.getTenHangLap());
        Log.d(TAG, "insertLoaiQuanAo: LoaiQuanAo: " + hangQuanAo.toString());
        Log.d(TAG, "insertLoaiQuanAo: Values: " + values);

        long ketqua = db.insert("LoaiQuanAo", null, values);
        if (ketqua > 0) {
            Log.d(TAG, "insertLoaiQuanAo: Thêm thành công");
        } else {
            Log.d(TAG, "insertLoaiQuanAo: Thêm thất bại");
        }
        db.close();
    }

    public void updateHangQuanAo(HangQuanAo hangQuanAo) {
        qlQuanAoDB = new QLQuanAoDB(context);
        db = qlQuanAoDB.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("maLoaiQuanAo", hangQuanAo.getMaHangLap());
        values.put("anhHang", hangQuanAo.getAnhHang());
        values.put("tenLoaiQuanAo", hangQuanAo.getTenHangLap());
        Log.d(TAG, "updateLoaiQuanAo: LoaiQuanAo: " + hangQuanAo.toString());
        Log.d(TAG, "updateLoaiQuanAo: Values: " + values);

        long ketqua = db.update("LoaiQuanAo", values, "maLoaiQuanAo=?", new String[]{String.valueOf(hangQuanAo.getMaHangLap())});
        if (ketqua > 0) {
            Log.d(TAG, "updateLoaiQuanAo: Sửa thành công");
        } else {
            Log.d(TAG, "updateLoaiQuanAo: Sửa thất bại");
        }
        db.close();
    }

    public void deleteHangQuanAo(HangQuanAo hangQuanAo) {
        qlQuanAoDB = new QLQuanAoDB(context);
        db = qlQuanAoDB.getWritableDatabase();
        Log.d(TAG, "deleteLoaiQuanAo: LoaiQuanAo: " + hangQuanAo.toString());

        long ketqua = db.delete("LoaiQuanAo", "maLoaiQuanAo=?", new String[]{String.valueOf(hangQuanAo.getMaHangLap())});
        if (ketqua > 0) {
            Log.d(TAG, "deleteLoaiQuanAo: Xóa thành công");
        } else {
            Log.d(TAG, "deleteLoaiQuanAo: Xóa thất bại");
        }
        db.close();
    }
}
