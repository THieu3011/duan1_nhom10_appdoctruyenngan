package com.example.duan1_nhom10.screen;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.duan1_nhom10.R;
import com.example.duan1_nhom10.dao.NguoiDungDAO;
import com.example.duan1_nhom10.features.BasicFeatures;
import com.example.duan1_nhom10.model.NguoiDung;

public class PolicyScreen extends AppCompatActivity {

    private NguoiDungDAO nguoiDungDAO;
    private BasicFeatures basicFeatures;
    private TextView tvDieuKhoan;
    private Button btnDaHieu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_policy_screen);

        basicFeatures=new BasicFeatures(PolicyScreen.this);
        nguoiDungDAO=new NguoiDungDAO(PolicyScreen.this);

        tvDieuKhoan=findViewById(R.id.policy_tvDieuKhoan);
        btnDaHieu=findViewById(R.id.policy_btnDaHieu);

        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        NguoiDung nguoiDung=nguoiDungDAO.traVeNguoiDung(bundle.getString("tenNguoiDung"));

        tvDieuKhoan.setMovementMethod(new ScrollingMovementMethod());
        String dieuKhoan="- Bất cứ lúc nào chúng tôi có quyền từ chối cung cấp cho bạn nơi để đăng " +
                "Tác phẩm hoặc Nội dung bản quyền bằng cách xóa chúng khỏi máy chủ của chúng tôi.\n\n" +
                "- Chúng tôi có quyền gửi tin nhắn, bao gồm cả tin nhắn e-mail thông qua cơ sở dữ liệu " +
                "của Người dùng.\n\n" +
                "- Chúng tôi có quyền hạn chế hoàn toàn hoặc một phần chức năng của Trang web " +
                "vì lý do kỹ thuật, phòng ngừa hoặc các lý do khác theo quyết định riêng của chúng tôi.\n\n" +
                "- Chúng tôi bảo lưu quyền, nhưng chúng tôi không cam kết theo dõi thông tin " +
                "hoặc tài liệu mà bạn đăng hoặc xuất bản để đọc chung.\n\n" +
                "- Chúng tôi có quyền, nhưng không có nghĩa vụ xóa thông tin hoặc tài liệu đó " +
                "mà không có bất kỳ giải thích hoặc thông báo nào.\n\n" +
                "Trang web của chúng tôi có thể chứa các liên kết đến các tài nguyên / " +
                "trang web của bên thứ ba không thuộc về chúng tôi hoặc do chúng tôi kiểm soát. " +
                "Chúng tôi không kiểm soát và chúng tôi không chịu bất kỳ trách nhiệm nào đối với nội dung, " +
                "chính sách bảo mật hoặc các hành động của các bên thứ ba. Ngoài ra, chúng tôi " +
                "sẽ không và không thể kiểm duyệt hoặc chỉnh sửa nội dung của các tài nguyên bên thứ ba. " +
                "Bằng cách sử dụng Trang web, bạn đảm bảo sự hiểu biết và chấp nhận các sự kiện nói trên " +
                "và đồng ý giải phóng cho chúng tôi khỏi trách nhiệm pháp lý phát sinh từ việc sử dụng " +
                "bất kỳ tài nguyên nào của bên thứ ba.";
        tvDieuKhoan.setText(dieuKhoan);

        btnDaHieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                basicFeatures.chuyenManNangCao(nguoiDung.getTenNguoiDung(), ComicStoreScreen.class);
            }
        });
    }
}