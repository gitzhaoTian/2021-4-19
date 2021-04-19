package com.example.baidu;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.btn_pu)
    Button btnPu;
    @BindView(R.id.btn_wei)
    Button btnWei;
    @BindView(R.id.btn_lu)
    Button btnLu;
    @BindView(R.id.btn_re)
    Button btnRe;
    private MapView mMapView = null;
    private LocationClient mLocationClient ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //获取地图控件引用
        mMapView = (MapView) findViewById(R.id.bmapView);

        mMapView.getMap().setMyLocationEnabled(true);
        mMapView.getMap().setIndoorEnable(true);//打开室内图，默认为关闭状态
//        mLocationClient  = new LocationClient(this);
//
////通过LocationClientOption设置LocationClient相关参数
//        LocationClientOption option = new LocationClientOption();
//        option.setOpenGps(true); // 打开gps
//        option.setCoorType("bd09ll"); // 设置坐标类型
//        option.setScanSpan(1000);
//
////设置locationClientOption
//        mLocationClient.setLocOption(option);
//
////注册LocationListener监听器
//        MyLocationListener myLocationListener = new MyLocationListener();
//        mLocationClient.registerLocationListener(myLocationListener);
////开启地图定位图层
//        mLocationClient.start();
//
//        MyLocationConfiguration.LocationMode mCurrentMode = MyLocationConfiguration.LocationMode.FOLLOWING;//定位跟随态
        //定位服务的客户端。宿主程序在客户端声明此类，并调用，目前只支持在主线程中启动
        LocationClient locationClient = new LocationClient(getApplicationContext());
//声明LocationClient类实例并配置定位参数
        LocationClientOption locationOption = new LocationClientOption();
        MyLocationListener myLocationListener = new MyLocationListener();
//注册监听函数
        locationClient.registerLocationListener(myLocationListener);
//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        locationOption.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
//可选，默认gcj02，设置返回的定位结果坐标系，如果配合百度地图使用，建议设置为bd09ll;
        locationOption.setCoorType("gcj02");
//可选，默认0，即仅定位一次，设置发起连续定位请求的间隔需要大于等于1000ms才是有效的
        locationOption.setScanSpan(1000);
//可选，设置是否需要地址信息，默认不需要
        locationOption.setIsNeedAddress(true);
//可选，设置是否需要地址描述
        locationOption.setIsNeedLocationDescribe(true);
//可选，设置是否需要设备方向结果
        locationOption.setNeedDeviceDirect(false);
//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
        locationOption.setLocationNotify(true);
//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        locationOption.setIgnoreKillProcess(true);
//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        locationOption.setIsNeedLocationDescribe(true);
//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        locationOption.setIsNeedLocationPoiList(true);
//可选，默认false，设置是否收集CRASH信息，默认收集
        locationOption.SetIgnoreCacheException(false);
//可选，默认false，设置是否开启Gps定位
        locationOption.setOpenGps(true);
//可选，默认false，设置定位时是否需要海拔信息，默认不需要，除基础定位版本都可用
        locationOption.setIsNeedAltitude(false);
//设置打开自动回调位置模式，该开关打开后，期间只要定位SDK检测到位置变化就会主动回调给开发者，该模式下开发者无需再关心定位间隔是多少，定位SDK本身发现位置变化就会及时回调给开发者
        locationOption.setOpenAutoNotifyMode();
//设置打开自动回调位置模式，该开关打开后，期间只要定位SDK检测到位置变化就会主动回调给开发者
        locationOption.setOpenAutoNotifyMode(3000,1, LocationClientOption.LOC_SENSITIVITY_HIGHT);
//需将配置好的LocationClientOption对象，通过setLocOption方法传递给LocationClient对象使用
        locationClient.setLocOption(locationOption);
//开始定位
        locationClient.start();

        MyLocationConfiguration myLocationConfiguration = new MyLocationConfiguration(MyLocationConfiguration.LocationMode.FOLLOWING, false, null);
        mMapView.getMap().setMyLocationConfiguration(myLocationConfiguration);

    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
//        mLocationClient.stop();
        mMapView.getMap().setMyLocationEnabled(false);
        mMapView.onDestroy();
        mMapView = null;
    }

    @OnClick({R.id.btn_pu, R.id.btn_wei, R.id.btn_lu, R.id.btn_re})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_pu:
                mMapView.getMap().setMapType(BaiduMap.MAP_TYPE_NONE);
                mMapView.getMap().setTrafficEnabled(false);
                mMapView.getMap().setBaiduHeatMapEnabled(false);
                mMapView.getMap().setMapType(BaiduMap.MAP_TYPE_NORMAL);
                break;
            case R.id.btn_wei:
                mMapView.getMap().setMapType(BaiduMap.MAP_TYPE_NONE);
                mMapView.getMap().setTrafficEnabled(false);
                mMapView.getMap().setBaiduHeatMapEnabled(false);
                mMapView.getMap().setMapType(BaiduMap.MAP_TYPE_SATELLITE);
                break;
            case R.id.btn_lu:
                mMapView.getMap().setMapType(BaiduMap.MAP_TYPE_NONE);
                mMapView.getMap().setMapType(BaiduMap.MAP_TYPE_NORMAL);
                mMapView.getMap().setBaiduHeatMapEnabled(false);
                mMapView.getMap().setTrafficEnabled(true);
                mMapView.getMap().setCustomTrafficColor("#ffba0101", "#fff33131", "#ffff9e19", "#00000000");
//  对地图状态做更新，否则可能不会触发渲染，造成样式定义无法立即生效。
                MapStatusUpdate u = MapStatusUpdateFactory.zoomTo(13);
                mMapView.getMap().animateMapStatus(u);
                break;
            case R.id.btn_re:
                mMapView.getMap().setMapType(BaiduMap.MAP_TYPE_NONE);
                mMapView.getMap().setMapType(BaiduMap.MAP_TYPE_NORMAL);
                mMapView.getMap().setBaiduHeatMapEnabled(true);
                break;
        }
    }
    public class MyLocationListener extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            //mapView 销毁后不在处理新接收的位置
            if (location == null || mMapView == null){
                return;
            }
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(location.getDirection()).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            mMapView.getMap().setMyLocationData(locData);
        }
    }
}