package com.example.donghe.wxfriend.activity;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.donghe.wxfriend.R;
import com.example.donghe.wxfriend.RecycleViewDivider;
import com.example.donghe.wxfriend.adapter.MyRecycleViewAdapter;
import com.example.donghe.wxfriend.bean.GridImageModle;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MyRecycleViewAdapter recycleViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new LoadTask().execute();
    }


    //异步加载图片
    class LoadTask extends AsyncTask<Void, Void, GridImageModle> {

        @Override
        protected GridImageModle doInBackground(Void... voids) {
            Gson gson = new Gson();
            GridImageModle itemModle = gson.fromJson(getData(), GridImageModle.class);

            return itemModle;
        }

        @Override
        protected void onPostExecute(GridImageModle itemModle) {
            super.onPostExecute(itemModle);
            recyclerView = (RecyclerView) findViewById(R.id.listview);
            recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
            recyclerView.addItemDecoration(new RecycleViewDivider(MainActivity.this, LinearLayoutManager.HORIZONTAL));
            recycleViewAdapter = new MyRecycleViewAdapter(MainActivity.this);
            recyclerView.setAdapter(recycleViewAdapter);
            recycleViewAdapter.setList(itemModle.getList());

        }
    }

    private String getData() {
        // 模拟网络获取数据
        String json = "{\"code\":200,\"msg\":\"ok\",list:["
                + "{\"id\":110,\"headImg\":\"https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=4059021347,2703226688&fm=23&gp=0.jpg\",\"name\":\"林志颖\",\"content\":\"天天开心!\",\"urls\":[]},"
                + "{\"id\":111,\"headImg\":\"http://image.cnwest.com/attachement/jpg/site1/20110507/001372d8a36f0f2f4c953a.jpg\",\"name\":\"李晨\",\"content\":\"我们\","
                + "  \"urls\":[\"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1486458146985&di=a6f91b30e2404018dd6adefd91624255&imgtype=0&src=http%3A%2F%2Fwww.bjsacp.com%2Fupimgs%2F20160507%2F012817483.jpg\"]},"

                + "{\"id\":114,\"headImg\":\"https://t2.27270.com/uploads/tu/201607/110/st14.jpg\",\"name\":\"张杰\",\"content\":\"新歌你听了吗\",\"urls\":["
                + "\"https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1486447863&di=5e32ebde56c226dcadd7ecd7ddbf6a09&src=http://meitu.qqzong.com/uploads/allimg/150131/014254CA-0.jpg\","

                + "\"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1486458146984&di=10fba2b5f905eced29fa9764543f4409&imgtype=0&src=http%3A%2F%2Fcdn.rouding.com%2Fimagesrc-s%2Fjpg%2F201308-7-5823D18D0212C69A-t.jpg\","
                + "\"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1486458146984&di=dfd8a70d7d6fead35cf16c2252a5b55c&imgtype=0&src=http%3A%2F%2Fwww.17uba.com%2Fupload%2Ffck%2F52688b89e3a18.jpg\","
                + "\"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1486458146984&di=7374bde21e5176784fa010ac8a6984eb&imgtype=0&src=http%3A%2F%2Fimg1.cache.netease.com%2Fhouse%2F2015%2F6%2F18%2F20150618101320f94b8.jpg\"]},"

                + "{\"id\":112,\"headImg\":\"http://img3.imgtn.bdimg.com/it/u=2712128750,3020473187&fm=23&gp=0.jpg\",\"name\":\"刘亦菲\",\"content\":\"好伤心\",\"urls\":[\"http://upload.cbg.cn/2015/0305/1425518659246.jpg\","
                + "\"http://www.people.com.cn/mediafile/pic/20150619/30/4179219540177204330.jpg\"]},"

                + "{\"id\":113,\"headImg\":\"https://t2.27270.com/uploads/tu/201606/43/24.jpg\",\"name\":\"郑凯\",\"content\":\"帅哥就是我\",\"urls\":[\"http://img4.3lian.com/img2005/06/13/29.jpg\",\"http://g.hiphotos.bdimg.com/album/s%3D680%3Bq%3D90/sign=e58fb67bc8ea15ce45eee301863b4bce/a5c27d1ed21b0ef4fd6140a0dcc451da80cb3e47.jpg\",\"http://img1.3lian.com/2015/w3/66/81.jpg\"]}]}";

        return json;
    }

}
