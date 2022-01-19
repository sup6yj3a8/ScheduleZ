package TSPTW;

import java.util.*;

import TSPTW.Event;

public class TSPTW {
    // Test function
//    public static void main(String[] args) {
//        Vector<Event> events1 = new Vector<Event>();
//        events1.add(new Event("start", new Position(0, 0, "國立中大學"),
//                new Time(30), new Time(30),new Time(30), Event.START, "None"));
//        events1.add(new Event("event1", new Position(50, 50),
//                new Time(3 * 60), new Time(5*60),new Time(30), Event.NEW, "None"));
//        events1.add(new Event("event2", new Position(10, 20),
//                new Time(1 * 60), new Time(5*60),new Time(30), Event.NEW, "None"));
//        events1.add(new Event("event3", new Position(75, 80),
//                new Time(4 * 60), new Time(6*60),new Time(30), Event.NEW, "None"));
//        events1.add(new Event("event4", new Position(111, 111),
//                new Time(1 * 60), new Time(10*60),new Time(60), Event.NEW, "None"));
//        events1.add(new Event("event5", new Position(99, 80),
//                new Time(13 * 60), new Time(13*60),new Time(60), Event.OLD, "None"));
//        events1.add(new Event("end", new Position(100, 100),
//                new Time(20 * 60), new Time(20 * 60),new Time(30), Event.END, "None"));
//
//        TSPTW tsptw = new TSPTW(events1, true);
//        tsptw.setSpeed(1);
//        tsptw.printEvents();
//        tsptw.printTravel();
//        tsptw.printCost();
//
//        if (tsptw.run()) {
//            tsptw.printEvents();
//            System.out.println(tsptw.getMinTravel());
//        } else {
//            System.out.println("Infeasible");
//        }
//        tsptw.printBestSeq();
//
//        Vector<Event> events2 = new Vector<Event>();
//        importDefaultData(events2);
//        TSPTW tsptw2 = new TSPTW(events2, false);
//
//        if (tsptw2.run()) {
//            tsptw2.printEvents();
//            System.out.println(tsptw2.getMinTravel());
//        } else {
//            System.out.println("Infeasible");
//        }
//        tsptw2.printBestSeq();
//    }

    static public void importDefaultData(Vector<Event> p_events) {
        // 1. Initialization.
        p_events.clear();
        Time baseTime = new Time(2022, 1, 14, 0, 0); // 2022/01/14 00:00
        final long base = baseTime.getTime();

        // 2. Add default events.
        // Kaohsiung
        p_events.add(new Event("中山大學出發！v(￣︶￣)y",Position.NSYSU,
                new Time(base + Time.DHMtoTime(0, 9, 0)),
                new Time(base + Time.DHMtoTime(0, 9, 0))
                ,new Time(0), Event.START, "六天五夜文青自由行GO!"));
        p_events.add(new Event("衛武營國家藝術文化中心",Position.Weiwuying,
                new Time(base + Time.DHMtoTime(0, 10, 0)),
                new Time(base + Time.DHMtoTime(0, 16, 0))
                ,new Time(90), Event.NEW, "這麼好拍不來嗎？"));
        p_events.add(new Event("大東藝術中心",Position.Dadong,
                new Time(base + Time.DHMtoTime(0, 10, 0)),
                new Time(base + Time.DHMtoTime(1, 16, 0))
                ,new Time(90), Event.NEW, "我是大藝術家！ ≧ω≦"));
        p_events.add(new Event("高雄市立美術館",Position.KaohsiungMuseum,
                new Time(base + Time.DHMtoTime(0, 10, 0)),
                new Time(base + Time.DHMtoTime(1, 16, 0))
                ,new Time(90), Event.NEW, "文青94要來美術館"));
        p_events.add(new Event("瑞豐夜市",Position.RuifengNightMarket,
                new Time(base + Time.DHMtoTime(0, 18, 0)),
                new Time(base + Time.DHMtoTime(0, 22, 0))
                ,new Time(120), Event.NEW, "我喜歡吃天使雞排～"));
        p_events.add(new Event("85大樓",Position.Kaohsiung85,
                new Time(base + Time.DHMtoTime(0, 23, 0)),
                new Time(base + Time.DHMtoTime(1, 9, 0))
                ,new Time(0), Event.OLD, "今晚來85過夜"));
        // Taichung
        p_events.add(new Event("咖波屋",Position.CapooHouse,
                new Time(base + Time.DHMtoTime(1, 10, 0)),
                new Time(base + Time.DHMtoTime(1, 16, 0))
                ,new Time(60), Event.NEW, "最愛咖波了(´∀`)"));
        p_events.add(new Event("宮原眼科",Position.Miyahara,
                new Time(base + Time.DHMtoTime(1, 10, 0)),
                new Time(base + Time.DHMtoTime(2, 16, 0))
                ,new Time(60), Event.NEW, "來眼科吃冰淇淋，有蝴蝶餅乾喔！"));
        p_events.add(new Event("台中港三井Outlet",Position.MitsuiOutle,
                new Time(base + Time.DHMtoTime(1, 10, 0)),
                new Time(base + Time.DHMtoTime(2, 16, 0))
                ,new Time(120), Event.NEW, "買爆！"));
        p_events.add(new Event("全安堂台灣台中太陽餅博物館",Position.SunCake,
                new Time(base + Time.DHMtoTime(2, 10, 0)),
                new Time(base + Time.DHMtoTime(2, 16, 0))
                ,new Time(60), Event.NEW, "我不喜歡吃太陽餅，但沒關係，這是要送人的≖‿≖"));
        p_events.add(new Event("高美濕地",Position.GaomeiWetland,
                new Time(base + Time.DHMtoTime(1, 10, 0)),
                new Time(base + Time.DHMtoTime(2, 16, 0))
                ,new Time(180), Event.NEW, "各位要愛護濕地，不要在溼地燒鋼琴"));
        p_events.add(new Event("台中日月千禧酒店",Position.MillenniumHotel,
                new Time(base + Time.DHMtoTime(1, 23, 0)),
                new Time(base + Time.DHMtoTime(2, 9, 0))
                ,new Time(0), Event.OLD, "日月千禧，易烊千璽"));
        p_events.add(new Event("逢甲夜市",Position.FengjiaNightMarket,
                new Time(base + Time.DHMtoTime(1, 18, 0)),
                new Time(base + Time.DHMtoTime(1, 22, 0))
                ,new Time(120), Event.NEW, "官芝霖豪吃"));
        p_events.add(new Event("一蘭拉麵台中店",Position.IchiranRamenTaichung,
                new Time(base + Time.DHMtoTime(1, 12, 0)),
                new Time(base + Time.DHMtoTime(2, 22, 0))
                ,new Time(90), Event.NEW, "台中新開的一蘭，我94跟風仔"));
        // Taipei
        p_events.add(new Event("圓山飯店",Position.GrandHotel,
                new Time(base + Time.DHMtoTime(2, 23, 0)),
                new Time(base + Time.DHMtoTime(3, 9, 0))
                ,new Time(0), Event.OLD, "來住圓山不為過吧"));
        p_events.add(new Event("士林夜市",Position.ShilinNightMarket,
                new Time(base + Time.DHMtoTime(2, 18, 0)),
                new Time(base + Time.DHMtoTime(5, 22, 0))
                ,new Time(120), Event.NEW, "士林夜市不在士林站"));
        p_events.add(new Event("九份老街",Position.JiufenOldStreet,
                new Time(base + Time.DHMtoTime(2, 10, 0)),
                new Time(base + Time.DHMtoTime(3, 16, 0))
                ,new Time(180), Event.NEW, "神隱少女，再吃下去會變成豬(;´༎ຶД༎ຶ`)"));
        p_events.add(new Event("淡水老街",Position.TamsuiOldStreet,
                new Time(base + Time.DHMtoTime(4, 10, 0)),
                new Time(base + Time.DHMtoTime(5, 16, 0))
                ,new Time(180), Event.NEW, "阿給其實是油豆腐包冬粉"));
        p_events.add(new Event("野柳地質公園",Position.YehliuGeopark,
                new Time(base + Time.DHMtoTime(3, 10, 0)),
                new Time(base + Time.DHMtoTime(5, 19, 0))
                ,new Time(60), Event.NEW, "(ノಠ益ಠ)歸剛誒！不要再摸女王頭了啦！"));
        p_events.add(new Event("北投麗禧溫泉酒店",Position.GrandViewResortBeitou,
                new Time(base + Time.DHMtoTime(3, 10, 0)),
                new Time(base + Time.DHMtoTime(5, 19, 0))
                ,new Time(180), Event.NEW, "泡泡溫泉94爽"));
        p_events.add(new Event("台北101",Position.Taipei101,
                new Time(base + Time.DHMtoTime(3, 23, 0)),
                new Time(base + Time.DHMtoTime(4, 9, 0))
                ,new Time(0), Event.OLD, "想不到吧！今晚住101"));
        p_events.add(new Event("福隆海水浴場",Position.FulongBeach,
                new Time(base + Time.DHMtoTime(3, 10, 0)),
                new Time(base + Time.DHMtoTime(5, 19, 0))
                ,new Time(150), Event.NEW, "搖起來搖起來"));
        // Yilan
        p_events.add(new Event("冬山伯朗大道",Position.BrownAvenue,
                new Time(base + Time.DHMtoTime(3, 10, 0)),
                new Time(base + Time.DHMtoTime(5, 19, 0))
                ,new Time(60), Event.NEW, "中山金城武是我(≧▽≦)"));
        p_events.add(new Event("蘇澳冷泉公園",Position.SuaoColdSpringPark,
                new Time(base + Time.DHMtoTime(3, 10, 0)),
                new Time(base + Time.DHMtoTime(5, 19, 0))
                ,new Time(120), Event.NEW, "好冷的冷泉"));
        p_events.add(new Event("羅東夜市",Position.LuodongNightMarket,
                new Time(base + Time.DHMtoTime(4, 18, 0)),
                new Time(base + Time.DHMtoTime(4, 22, 0))
                ,new Time(120), Event.NEW, "繼續吃"));
        p_events.add(new Event("悅川酒店",Position.WaldenHotel,
                new Time(base + Time.DHMtoTime(4, 23, 0)),
                new Time(base + Time.DHMtoTime(5, 9, 0))
                ,new Time(0), Event.OLD, "今天玩得好累"));
        p_events.add(new Event("到家囉～總統府",Position.OfficeOfThePresident,
                new Time(base + Time.DHMtoTime(5, 22, 0)),
                new Time(base + Time.DHMtoTime(5, 22, 0))
                ,new Time(0), Event.END, "意不意外，驚不驚喜，我家在總統府(─‿─)"));
    }

    // Constructor
    public TSPTW(Vector<Event> p_events, final boolean p_isPlane) {
        // 1. Initialization of events.
        events = p_events;
        eventSize = events.size();
        if (eventSize <= 2) {return;}
        Collections.sort(events);
        iterMaxSize = 10 * eventSize;
        isPlane = p_isPlane;

        // 2. Find the star and end event.
        //    Check startTime and endTime of the old event are same.
        for (int i = 0; i < eventSize; ++i) {
            if (events.get(i).type == Event.START) {      // Start event
                start = i;
            } else if (events.get(i).type == Event.END) { // End event
                end = i;
            } else if (events.get(i).type == Event.OLD && events.get(i).starTime.getTime() != events.get(i).endTime.getTime()) {
                Event event = events.get(i);
                event.serviceTime.setTime(event.endTime.getTime() - event.starTime.getTime());
                event.endTime.setTime(event.starTime.getTime());
                events.set(i, event);
            }
        }

        // 3.1 Initialization of best sequences.
        bestSeq.setSize(eventSize);
        for (int i = 0; i < eventSize; ++i) {bestSeq.set(i, i);}
        if (bestSeq.get(0) != start) {Collections.swap(bestSeq, 0, start);}
        if (bestSeq.get(eventSize - 1) != end) {Collections.swap(bestSeq, eventSize - 1, end);}
        // 3.2 Initialization of the index of sequences.
        indSeq.setSize(eventSize - 2);
        for (int i = 0; i < indSeq.size(); ++i) {indSeq.set(i, bestSeq.get(i + 1));}

        // 4. Initialization of the travel and cost table. (min)
        travelTable = new long[eventSize][eventSize];
        costTable = new long[eventSize][eventSize];
        for (int i = 0; i < eventSize; ++i) {
            for (int j = 0; j < eventSize; ++j) {
                double travel = Position.getDistance(events.get(i).position, events.get(j).position, isPlane) / speed;
                travelTable[i][j] = (long) travel;
                costTable[i][j] = travelTable[i][j] + events.get(i).serviceTime.getTime();
            }
        }
    }

    // VIG_VNS for TSPTW
    // Korhan Karabulut, M. Fatih Tasgetiren
    // A Variable Iterated Greedy Algorithm for The Traveling Salesman Problem with Time Windows
    // Information Sciences, 2014, Vol. 279, pp. 383-395
    public boolean run() {
        // 0. Check if the size, start and end of events.
        if (eventSize <= 2 || start == -1 || end == -1) {return false;}

        // 1. Initialization of the current sequence.
        Vector<Integer> currSeq = VNS_1_Opt(bestSeq);
        if (isBetter(currSeq, bestSeq)) {bestSeq = currSeq;}
        iterSize = 0;

        // 2. Execute variable iterated greedy (VIG) algorithm.
        while (iterSize++ < iterMaxSize) {
            int k = 1;
            do {
                // 2.1 Determine the number of destruction.
                int d = 5 * k;
                // 2.2 Execute destruction and construction.
                Vector<Integer> currSeq1 = deConstruct(currSeq, d);
                // 2.3 Execute a local search (VNS).
                Vector<Integer> currSeq2 = VNS_1_Opt(currSeq1);
                // 2.4 Check if the current sequence 2 (currSeq2) is better than the current sequence (currSeq).
                if (isBetter(currSeq2, currSeq)) {
                    k = 1;
                    currSeq = currSeq2;
                    // 2.5 Check if the current sequence (currSeq) is better than the best sequence (bestSeq).
                    if (isBetter(currSeq, bestSeq)) {bestSeq = currSeq;}
                } else {
                    ++k;
                }
            } while (k <= maxK);
        }

        // 3. Update the events.
        if (isFeasible) {
            minCost = fitness(bestSeq);
            minTravel = travelTime(bestSeq);
            updateEvents();
        }

        // 4. The best sequence is feasible?
        return isFeasible;
    }

    // Variable neighborhood search (VNS) for one optimal.
     private Vector<Integer> VNS_1_Opt(Vector<Integer> p_seq) {
        Vector<Integer> seq = (Vector<Integer>) p_seq.clone(); // Copy

         if (eventSize >= 4) {
             Vector<Integer> seq1 = backward_1_Opt(seq);
             if (isBetter(seq1, seq)) {
                 seq = seq1;
             }

             Vector<Integer> seq2 = forward_1_Opt(seq);
             if (isBetter(seq2, seq)) {
                 seq = seq2;
             }
         }

        return seq;
     }

    private Vector<Integer> backward_1_Opt(Vector<Integer> p_seq) {
        Vector<Integer> seq = (Vector<Integer>) p_seq.clone(); // Copy

        for (int i = eventSize - 2; i >= 2; --i) {
            final double remove = costTable[seq.get(i - 1)][seq.get(i)    ] +
                                  costTable[seq.get(i)    ][seq.get(i + 1)] -
                                  costTable[seq.get(i - 1)][seq.get(i + 1)];
            for (int j = i - 1; j >= 1; --j) {
                final double add = costTable[seq.get(j - 1)][seq.get(i)] +
                                   costTable[seq.get(i)    ][seq.get(j)] -
                                   costTable[seq.get(j - 1)][seq.get(j)];
                if ( (add - remove) < 0 && isFeasible(seq, i, j)) {
                    Vector<Integer> seq1 = insert(seq, i, j);
                    if (isBetter(seq1, seq)) {seq = seq1;}
                }
            }
        }

        return seq;
    }

    private Vector<Integer> forward_1_Opt(Vector<Integer> p_seq) {
        Vector<Integer> seq = (Vector<Integer>) p_seq.clone(); // Copy

        for (int i = 1; i <= eventSize - 3; ++i) {
            final double remove = costTable[seq.get(i - 1)][seq.get(i)    ] +
                                  costTable[seq.get(i)    ][seq.get(i + 1)] -
                                  costTable[seq.get(i - 1)][seq.get(i + 1)];
            for (int j = i + 1; j <= eventSize - 2; ++j) {
                final double add = costTable[seq.get(i)][seq.get(j + 1)] +
                                   costTable[seq.get(j)][seq.get(i)    ] -
                                   costTable[seq.get(j)][seq.get(j + 1)];
                if ( (add - remove) < 0 && isFeasible(seq, i, j)) {
                    Vector<Integer> seq1 = insert(seq, i, j);
                    if (isBetter(seq1, seq)) {seq = seq1;}
                }
            }
        }

        return seq;
    }

    private Vector<Integer> insert(Vector<Integer> p_seq, final int i, final int j) {
        Vector<Integer> seq = (Vector<Integer>) p_seq.clone(); // Copy
        Integer tmp = seq.remove(i);
        // Shifts the element currently at that position and any subsequent elements to the right
        seq.add(j, tmp);
        return seq;
    }

    private boolean isFeasible(Vector<Integer> seq, final int i, final int j) {
        long arrive1 = events.get(seq.get(j)).starTime.getTime() + costTable[seq.get(j)][seq.get(i)    ];
        long arrive2 = events.get(seq.get(i)).starTime.getTime() + costTable[seq.get(i)][seq.get(j + 1)];
        return  arrive1 <= events.get(seq.get(i)    ).endTime.getTime() &&
                arrive2 <= events.get(seq.get(j + 1)).endTime.getTime();
    }

    // Execute destruction and construction.
    private Vector<Integer> deConstruct(Vector<Integer> p_seq, int d) {
        Vector<Integer> seq = (Vector<Integer>) p_seq.clone(); // Copy
        if (d > eventSize - 2) {d = eventSize - 2;}
        Collections.shuffle(indSeq);

        // 1. Destruction.
        Vector<Integer> desSeq = new Vector<Integer>(); // The destruction of sequence.
        for (int i = 0; i < d; ++i) {desSeq.add(seq.get(indSeq.get(i)));}
        seq.removeAll(desSeq);

        // 2. Construction
        for (int i = 0; i < d; ++i) {
            if (seq.size() == 2) {
                seq.add(1, desSeq.get(i));
                continue;
            }
            Vector<Integer> currSeq1 = (Vector<Integer>) seq.clone();
            currSeq1.add(1, desSeq.get(i));
            for (int j = 2; j < seq.size() - 1; ++j) {
                Vector<Integer> currSeq2 = (Vector<Integer>) seq.clone();
                currSeq2.add(j, desSeq.get(i));
                if (isBetter(currSeq2, currSeq1)) {currSeq1 = currSeq2;}
            }
            seq = currSeq1;
        }
        return seq;
    }

    // Using superiority of feasible solutions (SF) to determine weather seq1 is better seq2.
    private boolean isBetter(Vector<Integer> seq1, Vector<Integer> seq2) {
        // 1. Count the number of infeasible events.
        final int infeasible1 = isFeasible(seq1);
        final int infeasible2 = isFeasible(seq2);
        if ( (infeasible1 == 0 || infeasible2 == 0) && seq1.size() == eventSize) {
            isFeasible = true;
        }

        // 2. Superiority of feasible solutions (SF)
        if (infeasible1 != 0 && infeasible2 != 0 && infeasible1 != infeasible2) {
            return infeasible1 < infeasible2;
        } else if (infeasible1 == 0 && infeasible2 != 0) {
            return true;
        } else if (infeasible1 == 0 && infeasible2 == 0 || infeasible1 == infeasible2){
            final double NFT = NFT0 / (1 + lamda * iterSize);
            final double fitness1 = fitness(seq1) + Math.pow(infeasible1 / NFT, alpha);
            final double fitness2 = fitness(seq2) + Math.pow(infeasible2 / NFT, alpha);
            if (fitness1 < fitness2) {
                if (infeasible1 == 0 && seq1.size() == eventSize) {isFeasible = true;}
                return true;
            }
        }

        // 3. seq1 is infeasible or fitness1 is bigger.
        return false;
    }

    private int isFeasible(Vector<Integer> seq) {
        int size = 0;
        long arrive = 0;    // The arrival time.
        long departure = 0; // The departure time.

        for (int i = 1; i < seq.size(); ++i) {
            // 1. D[i - 1] = max(A[i - 1], startTime[i - 1]).
            departure = Math.max(arrive, events.get(seq.get(i - 1)).starTime.getTime());
            // 2. A[i] = D[i - 1] + cost[i - 1][i].
            arrive = departure + costTable[seq.get(i - 1)][seq.get(i)];
            // 3. A[i] > endTime[i] -> infeasible
            if (arrive > events.get(seq.get(i)).endTime.getTime()) {++size;}
        }

        return size;
    }

    private double fitness(Vector<Integer> seq) {
        double sum = 0.0;
        for (int i = 0; i < seq.size() - 1; ++i) {
            sum += costTable[seq.get(i)][seq.get(i + 1)];
        }
        return sum;
    }

    private double travelTime(Vector<Integer> seq){
        double sum = 0.0;
        for (int i = 0; i < seq.size() - 1; ++i) {
            sum += travelTable[seq.get(i)][seq.get(i + 1)];
        }
        return sum;
    }

    // Set function
    public void setSpeed(final double speed) {
        this.speed = speed;
    }

    public void setIterMaxSize(int iterMaxSize) {
        this.iterMaxSize = iterMaxSize;
    }

    public void setPlane(boolean plane) {
        isPlane = plane;
    }

    public void setBestSeq(int[] seq) {
        for (int i = 0; i < eventSize; ++i) {
            bestSeq.set(i, seq[i]);
        }
    }

    public boolean updateEvents() {
        // 1. Calculate the arrival and departure time.
        long arrive = 0;    // The arrival time.
        long departure = events.get(bestSeq.get(0)).endTime.getTime(); // The departure time.
        for (int i = 1; i < bestSeq.size() - 1; ++i) {
            // 1. A[i] = max {D[i - 1] + travel[i - 1][i], starTime[i]}
            arrive = Math.max(departure + travelTable[bestSeq.get(i - 1)][bestSeq.get(i)],
                              events.get(bestSeq.get(i)).starTime.getTime());
            // 2. D[i] = A[i] + serviceTime[i]
            departure = arrive + events.get(bestSeq.get(i)).serviceTime.getTime();
            // 3. Update the window time.
            Event event = events.get(bestSeq.get(i));
            event.setTimeWindow(arrive, departure);
            events.set(bestSeq.get(i), event);
        }

        // 2. Sort the events.
        Collections.sort(events);

        return true;
    }


    // Get function
    public boolean isFeasible() {
        return isFeasible;
    }

    public double getMinCost() {
        return minCost;
    }

    public double getMinTravel() {
        return minTravel;
    }

    // Print function
    public void printEvents() {
        for (int i = 0; i < eventSize; ++i) {
            System.out.println(events.get(i));
        }
    }

    public void printTravel() {
        System.out.println("Travel table :");
        printTable(travelTable);
    }

    public void printCost() {
        System.out.println("Cost table :");
        printTable(costTable);
    }

    private void printTable(long[][] table) {
        System.out.print("    ");
        for (int i = 0; i < eventSize; ++i) {System.out.printf("%3d ", i);}
        System.out.print('\n');
        for (int i = 0; i <eventSize; ++ i) {
            System.out.printf("%3d ", i);
            for (int j = 0; j < eventSize; ++j) {
                System.out.printf("%3d ", table[i][j]);
            }
            System.out.print('\n');
        }
        System.out.print('\n');
    }

    public void printBestSeq() {
        System.out.println("Best sequences : " + bestSeq + '\n');
    }

    // Data
    // Events
    private Vector<Event> events;
    private int eventSize;

    // Basic parameters
    private int start = -1;
    private int end = -1;
    private Boolean isFeasible = false;

    private Vector<Integer> bestSeq = new Vector<Integer>();
    private Vector<Integer> indSeq = new Vector<Integer>();
    private double speed = 30.0 / 60.0; // 60 km/hr = 1.0 km/min, 30 km / hr = 0.5 km / min
    private long[][] travelTable; // Travel time (min)
    private long[][] costTable;   // Cost time (min) = travel + service.
    private double minTravel = Double.MAX_VALUE;
    private double minCost = Double.MAX_VALUE;
    private boolean isPlane = true;

    // VIG_VNS parameters
    private int iterMaxSize;
    private int iterSize = 0;
    int maxK = (eventSize - 2) / 5;

    // Adaptive penalty approach parameters
    final double NFT0 = 0.001;
    final double lamda = 0.04;
    final int alpha = 2;
}
