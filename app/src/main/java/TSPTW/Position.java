package TSPTW;

public class Position {
    // Function
    public Position(final double p_x, final double p_y) {
        setVal(p_x, p_y);
    }

    public Position(final String p_name, final double p_x, final double p_y) {
        name = p_name;
        setVal(p_x, p_y);
    }

    public Position(final double p_x, final double p_y, final String p_name) {
        setVal(p_x, p_y);
        name = p_name;
    }

    // Get function
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getLatitude() {
        return x;
    }

    public double getLongitude() {
        return y;
    }

    public String getName() {
        return name;
    }

    public static double getDistance(final Position pos1, final Position pos2, final boolean isPlane) {
        if (isPlane) { // Plane distance : (x, y) is a point on the plane.
            final double x1 = pos1.getX();
            final double x2 = pos2.getX();
            final double y1 = pos1.getY();
            final double y2 = pos2.getY();
            return Math.sqrt(Math.pow(x2 - x1, 2) +
                             Math.pow(y2 - y1, 2));
        } else {       // Great-circle distance : (lat, long) is a point on the Earth.
            final double lat1 = pos1.getLatitude();
            final double lat2 = pos2.getLatitude();
            final double long1 = pos1.getLongitude();
            final double long2 = pos2.getLongitude();
            final double C = Math.sin(rad(lat1)) * Math.sin(rad(lat2)) +
                             Math.cos(rad(lat1)) * Math.cos(rad(lat2)) *
                             Math.cos(rad(long1- long2));
            return Position.earthR * Math.acos(C);
        }
    }

    // Degree -> radius
    private static double rad(final double d) {
        return d * Math.PI / 180.0;
    }

    // Set function
    public void setX(final double p_x) {
        x = p_x;
    }

    public void setY(final double p_y) {
        y = p_y;
    }

    public void setLatitude(final double lat) {
        setX(lat);
    }

    public void setLongitude(final double lon) {
        setY(lon);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVal(final double p_x, final double p_y) {
        x = p_x;
        y = p_y;
    }

    public void setVal(final double p_x, final double p_y, final String p_name) {
        x = p_x;
        y = p_y;
        name = p_name;
    }

    public void setVal(final String p_name, final double p_x, final double p_y) {
        x = p_x;
        y = p_y;
        name = p_name;
    }

    @Override
    public String toString() {
        String p_name = name;
        if (p_name != "") {p_name = " " + p_name;}
        return "\"Position\":\"" + p_name +" (" + String.format("%.4f", x) + ", " +  String.format("%.4f", y) + ")\"";
    }

    public String toNameString() {
        return  name;
    }

    public String toLoatLonString() {
        return "(" + String.format("%.4f", x) + ", " + String.format("%.4f", y) + ")";
    }

    // Data
    private double x = 0.0;       // x = Latitude 緯度
    private double y = 0.0;       // y = Longitude 經度
    private String name = "";
    public static final double earthR = 6371.004; // Earth radius. (km)

    // Default position
    // Taiwan High Speed Rail (HSR)
    static public Position HSR_Kaohsiung = new Position("高鐵左營站",22.688087064476747, 120.30969370620731);
    static public Position HSR_Taichung = new Position("高鐵台中站", 24.112142306608295, 120.61615024557105);
    static public Position HSR_Taipei = new Position("高鐵台北站", 25.048345771007945, 121.51772416244124);

    // Night markets
    static public Position RuifengNightMarket = new Position("瑞豐夜市", 22.666104405188484, 120.29978975684014);
    static public Position FengjiaNightMarket = new Position("逢甲夜市", 24.17995446157122, 120.6481561809475);
    static public Position ShilinNightMarket = new Position("士林夜市", 25.08819094954326, 121.52407365367179);
    static public Position LuodongNightMarket = new Position("羅東夜市", 24.67826014766204, 121.76959334699328);

    // Hotels
    static public Position MillenniumHotel = new Position("台中日月千禧酒店", 24.1567272497621, 120.64202495263628);
    static public Position GrandHotel = new Position("台北圓山大飯店", 25.07891128465635, 121.52652066756745);
    static public Position WaldenHotel = new Position("宜蘭悅川酒店", 24.769771803497523, 121.75660180812702);

    // Sightseeing spots in Kaohsiung
    static public Position NSYSU = new Position("國立中山大學",22.628328414814924, 120.26474445515647);
    static public Position Kaohsiung85 = new Position("高雄85大樓", 22.611728731974207, 120.30026575945863);
    static public Position Weiwuying = new Position("衛武營國家藝術文化中心", 22.619773785645272, 120.33955268637919);
    static public Position Dadong = new Position("大東文化藝術中心", 22.625600065089376, 120.36359100359344);
    static public Position KaohsiungMuseum = new Position("高雄市立美術館", 22.65905319066177, 120.2870660839538);
    static public Position CaffainaCoffee = new Position("卡啡那明誠店", 22.660833596587164, 120.30999867254451);

    // Sightseeing spots in Taichung
    static public Position CapooHouse = new Position("咖波屋", 24.14759063856579, 120.66748561621118);
    static public Position Miyahara = new Position("宮原眼科", 24.139080760806266, 120.68488553149987);
    static public Position MitsuiOutle = new Position("台中港三井Outlet", 24.259349813626716, 120.51867807884416);
    static public Position SunCake = new Position("全安堂台灣台中太陽餅博物館", 24.140817993930398, 120.68369328547664);
    static public Position GaomeiWetland = new Position("高美濕地", 24.311943833415118, 120.54981366724078);
    static public Position IchiranRamenTaichung = new Position("一蘭拉麵台中店", 24.168713689612485, 120.63868410101823);

    // Sightseeing spots in Taipei
    static public Position Taipei101 = new Position("台北101大樓", 25.033807729329467, 121.56455691899578);
    static public Position JiufenOldStreet = new Position("九份老街", 25.11033555810907, 121.84745716928438);
    static public Position TamsuiOldStreet = new Position("淡水老街", 25.168931574674996, 121.44409404364167);
    static public Position YehliuGeopark = new Position("野柳地質公園", 25.209541203853227, 121.6948336954555);
    static public Position GrandViewResortBeitou = new Position("北投麗禧溫泉酒店", 25.138336352578428, 121.5149041255236);
    static public Position FulongBeach = new Position("福隆海水浴場", 25.021962142416555, 121.94320211431918);
    static public Position OfficeOfThePresident = new Position("中華民國總統府", 25.040000670162865, 121.51193957166146);

    // Sightseeing spots in Yilan
    static public Position BrownAvenue = new Position("宜蘭冬山伯朗大道", 24.645455897803377, 121.81406448375704);
    static public Position SuaoColdSpringPark = new Position("蘇澳冷泉公園", 24.59771236479345, 121.8511321531081);

    // Test function
//    public static void main(String[] args) {
//        double dst = Position.getDistance(Position.HSR_Kaohsiung, Position.HSR_Taichung, false);
//        System.out.println(Position.NSYSU);
//        System.out.println(Position.HSR_Kaohsiung);
//        System.out.println(dst);
//        System.out.println(Position.HSR_Kaohsiung.toNameString());
//        System.out.println(Position.HSR_Kaohsiung.toLoatLonString());
//    }
}
