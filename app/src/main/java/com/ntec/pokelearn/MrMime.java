package com.ntec.pokelearn;

        import androidx.appcompat.app.AppCompatActivity;
        import androidx.recyclerview.widget.GridLayoutManager;
        import androidx.recyclerview.widget.RecyclerView;
        import android.app.Dialog;
        import android.content.Intent;
        import android.graphics.Color;
        import android.graphics.drawable.ColorDrawable;
        import android.os.Bundle;
        import android.os.Handler;
        import android.view.View;
        import android.widget.ImageView;
        import android.widget.TextView;
        import java.util.ArrayList;
        import java.util.Arrays;
        import java.util.Collections;
        import java.util.Objects;


public class MrMime extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "Mr.Mime";
    private Dialog mDialog_instruction;
    private ImageView ivInfo, ivQuit, mImageView, dltBtn, ansCorrect, ansFalse;
    private TextView alpA, alpB, alpC, alpD, alpE, alpF, alpG, alpH, alpI, alpJ, alpK, alpL, alpM, alpN, alpO,
            alpP, alpQ, alpR, alpS, alpT, alpU, alpV, alpW, alpX, alpY, alpZ;
    private TextView mCloseDialog, mTextView;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    MimeRecyclerViewAdapter recyclerViewAdapter;
    int qCount = 0, count=0;
    ArrayList<String> transBox;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mr_mime);

        transBox = new ArrayList<>();

        ivInfo = findViewById(R.id.imgInfo);
        ivQuit = findViewById(R.id.imgReturn);
        alpA = findViewById(R.id.tvA);
        alpB = findViewById(R.id.tvB);
        alpC = findViewById(R.id.tvC);
        alpD = findViewById(R.id.tvD);
        alpE = findViewById(R.id.tvE);
        alpF = findViewById(R.id.tvF);
        alpG = findViewById(R.id.tvG);
        alpH = findViewById(R.id.tvH);
        alpI = findViewById(R.id.tvI);
        alpJ = findViewById(R.id.tvJ);
        alpK = findViewById(R.id.tvK);
        alpL = findViewById(R.id.tvL);
        alpM = findViewById(R.id.tvM);
        alpN = findViewById(R.id.tvN);
        alpO = findViewById(R.id.tvO);
        alpP = findViewById(R.id.tvP);
        alpQ = findViewById(R.id.tvQ);
        alpR = findViewById(R.id.tvR);
        alpS = findViewById(R.id.tvS);
        alpT = findViewById(R.id.tvT);
        alpU = findViewById(R.id.tvU);
        alpV = findViewById(R.id.tvV);
        alpW = findViewById(R.id.tvW);
        alpX = findViewById(R.id.tvX);
        alpY = findViewById(R.id.tvY);
        alpZ = findViewById(R.id.tvZ);
        dltBtn = findViewById(R.id.dlt);
        ansCorrect = findViewById(R.id.correct);
        ansFalse = findViewById(R.id.incorrect);

        alpA.setOnClickListener(this);
        alpB.setOnClickListener(this);
        alpC.setOnClickListener(this);
        alpD.setOnClickListener(this);
        alpE.setOnClickListener(this);
        alpF.setOnClickListener(this);
        alpG.setOnClickListener(this);
        alpH.setOnClickListener(this);
        alpI.setOnClickListener(this);
        alpJ.setOnClickListener(this);
        alpK.setOnClickListener(this);
        alpL.setOnClickListener(this);
        alpM.setOnClickListener(this);
        alpN.setOnClickListener(this);
        alpO.setOnClickListener(this);
        alpP.setOnClickListener(this);
        alpQ.setOnClickListener(this);
        alpR.setOnClickListener(this);
        alpS.setOnClickListener(this);
        alpT.setOnClickListener(this);
        alpU.setOnClickListener(this);
        alpV.setOnClickListener(this);
        alpW.setOnClickListener(this);
        alpX.setOnClickListener(this);
        alpY.setOnClickListener(this);
        alpZ.setOnClickListener(this);
        dltBtn.setOnClickListener(this);

        shuffleQuestions();
        for(int i = 0; i < qArray[qCount].getAnsCheck().length(); i++){
            transBox.add("");
        }

        recyclerView = findViewById(R.id.ansRecycler);
        layoutManager = new GridLayoutManager(this,6);
        recyclerView.setLayoutManager(layoutManager);
        recyclerViewAdapter = new MimeRecyclerViewAdapter(this, transBox);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setHasFixedSize(true);
        mImageView = findViewById(R.id.imgQuestion);
        mTextView = findViewById(R.id.facts);
        mDialog_instruction = new Dialog(this);
        handler = new Handler();

        showRandomQuestion();

        ivInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowDialogInstruction();
            }
        });

        ivQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MrMime.this, MainActivity.class));
            }
        });
    }

    public void showRandomQuestion(){
        mImageView.setImageResource(qArray[qCount].getmImage());
        mTextView.setText(qArray[qCount].getmFacts());
    }

    MimeQuestions q01 = new MimeQuestions("APPLE", R.drawable.apple, "A round fruit of a tree of the rose family with thin green or red skin and crisp flesh.");
    MimeQuestions q02 = new MimeQuestions("ANT", R.drawable.ant, "A small insect with a sting, six legs, mostly red or black colored body.");
    MimeQuestions q03 = new MimeQuestions("BALL", R.drawable.ball, "A solid or hollow spherical or egg-shaped object that is kicked, thrown, or hit in a game.");
    MimeQuestions q04 = new MimeQuestions("BANANA", R.drawable.banana,"A long curved fruit which grows in clusters and yellow skin when ripe.");
    MimeQuestions q05 = new MimeQuestions("BULB", R.drawable.bulb1,"A glass-like item that provides light by passing an electric current through a filament");
    MimeQuestions q06 = new MimeQuestions("CANDLE", R.drawable.candle, "A cylinder or block of wax with a central wick which is lit to produce light as it burns.");
    MimeQuestions q07 = new MimeQuestions("CAR", R.drawable.car, "A road vehicle with four wheels, powered by engine to carry passengers.");
    MimeQuestions q08 = new MimeQuestions("COIN", R.drawable.coin,"A flat disc or piece of metal with an official stamp, used as money.");
    MimeQuestions q09 = new MimeQuestions("COW", R.drawable.cow, "A fully grown female animal that produce milk or beef.");
    MimeQuestions q10 = new MimeQuestions("CRAYON", R.drawable.crayon, "A pencil or stick of coloured chalk or wax, used for drawing.");
    MimeQuestions q11 = new MimeQuestions("DOG", R.drawable.dog,"A carnivorous mammal with long snout, an acute sense of smell and a barking voice.");
    MimeQuestions q12 = new MimeQuestions("ERASER", R.drawable.eraser,"A piece of soft rubber or plastic used to rub out something written.");
    MimeQuestions q13 = new MimeQuestions("FISH", R.drawable.fish,"A limbless cold-blooded vertebrate animal with gills and fins living wholly in water.");
    MimeQuestions q14 = new MimeQuestions("FORK", R.drawable.fork,"A tool used for lifting food to the mouth along with spoon or holding it when cutting with a knife.");
    MimeQuestions q15 = new MimeQuestions("KEY", R.drawable.key,"A small piece of shaped metal which is inserted into a lock and turned to unlock or lock it.");
    MimeQuestions q16 = new MimeQuestions("KITE", R.drawable.kite,"A toy flown in the wind at the end of a long string.");
    MimeQuestions q17 = new MimeQuestions("KNIFE", R.drawable.knife,"An instrument composed of a blade fixed into a handle, used for cutting or as a weapon.");
    MimeQuestions q18 = new MimeQuestions("LION", R.drawable.lion,"A large tawny-coloured cat that lives in prides which the male has a flowing shaggy mane.");
    MimeQuestions q19 = new MimeQuestions("NAIL", R.drawable.nail,"A small metal spike with a broadened flat head, driven into wood to join things together.");
    MimeQuestions q20 = new MimeQuestions("PAPAYA", R.drawable.papaya,"A tropical fruit shaped like an elongated melon, with edible orange flesh and small black seeds.");
    MimeQuestions q21 = new MimeQuestions("PENCIL", R.drawable.pencil,"An instrument for writing or drawing, made of a thin stick of graphite covered in a cylindrical case.");
    MimeQuestions q22 = new MimeQuestions("PIG", R.drawable.pig,"An omnivorous hoofed mammal with sparse bristly hair and a flat snout, it is kept for its meat (pork).");
    MimeQuestions q23 = new MimeQuestions("RULER", R.drawable.ruler,"A straight strip or cylinder of plastic, wood, metal, used to draw straight lines or measure distances.");
    MimeQuestions q24 = new MimeQuestions("SPONGE", R.drawable.sponge,"A piece of a soft, light, porous absorbent substance, used for washing and cleaning.");
    MimeQuestions q25 = new MimeQuestions("SPOON", R.drawable.spoon,"An implement consisting of a small oval or round bowl on a long handle, used for eating.");
    MimeQuestions q26 = new MimeQuestions("TOMATO", R.drawable.tomato,"A glossy red, or occasionally yellow, pulpy edible fruit that is eaten as a vegetable or in salad.");
    MimeQuestions q27 = new MimeQuestions("TREE", R.drawable.tree,"A tall plant with a trunk and branches made of wood, it has many roots.");
    MimeQuestions q28 = new MimeQuestions("WATER", R.drawable.water,"A colourless, transparent, odourless liquid that human drink as daily needs");

    MimeQuestions[]qArray=new MimeQuestions[]{
            q01, q02, q03, q04, q05, q06, q07, q08, q09, q10, q11, q12, q13, q14, q15, q16, q17, q18, q19, q20, q21, q22, q23, q24, q25, q26, q27, q28
    };

    public void shuffleQuestions(){
        Collections.shuffle(Arrays.asList(qArray));
    }

    public void ShowDialogInstruction() {
        mDialog_instruction.setContentView(R.layout.mime_instruction);
        mCloseDialog = mDialog_instruction.findViewById(R.id.custom_pop_up_random_close);

        mCloseDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog_instruction.dismiss();
            }
        });

        Objects.requireNonNull(mDialog_instruction.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mDialog_instruction.setCanceledOnTouchOutside(false);
        mDialog_instruction.show();
    }

    public void answerChecking(){
        int correctCount = 0;
        if (count==qArray[qCount].getAnsCheck().length()){
            for (int i=0; i< qArray[qCount].getAnsCheck().length(); i++){
                if (String.valueOf(qArray[qCount].getAnsCheck().charAt(i)).equals(transBox.get(i))){
                    correctCount++;
                }
            }
            if (correctCount == qArray[qCount].getAnsCheck().length()){
                ansCorrect.setVisibility(View.VISIBLE);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        transBox.clear();
                        count = 0;
                        qCount++;
                        for(int i = 0; i < qArray[qCount].getAnsCheck().length(); i++){
                            transBox.add("");
                        }
                        mImageView.setImageResource(qArray[qCount].getmImage());
                        mTextView.setText(qArray[qCount].getmFacts());
                        recyclerViewAdapter.notifyDataSetChanged();
                        ansCorrect.setVisibility(View.INVISIBLE);
                    }
                }, 1500);
                if (qCount>27){
                    qCount=0;
                    shuffleQuestions();
                }
            }
            else {
                ansFalse.setVisibility(View.VISIBLE);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        transBox.clear();
                        for(int i = 0; i < qArray[qCount].getAnsCheck().length(); i++){
                            transBox.add("");
                        }
                        count = 0;
                        recyclerViewAdapter.notifyDataSetChanged();
                        ansFalse.setVisibility(View.INVISIBLE);
                    }
                }, 1500);
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (count < qArray[qCount].getAnsCheck().length()) {
            switch (v.getId()) {
                case R.id.tvA:
                    if (transBox.get(count)=="") {
                        transBox.set(count,"A");
                        count++;
                    }
                    break;
                case R.id.tvB:
                    if (transBox.get(count)=="") {
                        transBox.set(count,"B");
                        count++;
                    }
                    break;
                case R.id.tvC:
                    if (transBox.get(count)=="") {
                        transBox.set(count,"C");
                        count++;
                    }
                    break;
                case R.id.tvD:
                    if (transBox.get(count)=="") {
                        transBox.set(count,"D");
                        count++;
                    }
                    break;
                case R.id.tvE:
                    if (transBox.get(count)=="") {
                        transBox.set(count,"E");
                        count++;
                    }
                    break;
                case R.id.tvF:
                    if (transBox.get(count)=="") {
                        transBox.set(count,"F");
                        count++;
                    }
                    break;
                case R.id.tvG:
                    if (transBox.get(count)=="") {
                        transBox.set(count,"G");
                        count++;
                    }
                    break;
                case R.id.tvH:
                    if (transBox.get(count)=="") {
                        transBox.set(count,"H");
                        count++;
                    }
                    break;
                case R.id.tvI:
                    if (transBox.get(count)=="") {
                        transBox.set(count,"I");
                        count++;
                    }
                    break;
                case R.id.tvJ:
                    if (transBox.get(count)=="") {
                        transBox.set(count,"J");
                        count++;
                    }
                    break;
                case R.id.tvK:
                    if (transBox.get(count)=="") {
                        transBox.set(count,"K");
                        count++;
                    }
                    break;
                case R.id.tvL:
                    if (transBox.get(count)=="") {
                        transBox.set(count,"L");
                        count++;
                    }
                    break;
                case R.id.tvM:
                    if (transBox.get(count)=="") {
                        transBox.set(count,"M");
                        count++;
                    }
                    break;
                case R.id.tvN:
                    if (transBox.get(count)=="") {
                        transBox.set(count,"N");
                        count++;
                    }
                    break;
                case R.id.tvO:
                    if (transBox.get(count)=="") {
                        transBox.set(count,"O");
                        count++;
                    }
                    break;
                case R.id.tvP:
                    if (transBox.get(count)=="") {
                        transBox.set(count,"P");
                        count++;
                    }
                    break;
                case R.id.tvQ:
                    if (transBox.get(count)=="") {
                        transBox.set(count,"Q");
                        count++;
                    }
                    break;
                case R.id.tvR:
                    if (transBox.get(count)=="") {
                        transBox.set(count,"R");
                        count++;
                    }
                    break;
                case R.id.tvS:
                    if (transBox.get(count)=="") {
                        transBox.set(count,"S");
                        count++;
                    }
                    break;
                case R.id.tvT:
                    if (transBox.get(count)=="") {
                        transBox.set(count,"T");
                        count++;
                    }
                    break;
                case R.id.tvU:
                    if (transBox.get(count)=="") {
                        transBox.set(count,"U");
                        count++;
                    }
                    break;
                case R.id.tvV:
                    if (transBox.get(count)=="") {
                        transBox.set(count,"V");
                        count++;
                    }
                    break;
                case R.id.tvW:
                    if (transBox.get(count)=="") {
                        transBox.set(count,"W");
                        count++;
                    }
                    break;
                case R.id.tvX:
                    if (transBox.get(count)=="") {
                        transBox.set(count,"X");
                        count++;
                    }
                    break;
                case R.id.tvY:
                    if (transBox.get(count)=="") {
                        transBox.set(count,"Y");
                        count++;
                    }
                    break;
                case R.id.tvZ:
                    if (transBox.get(count)=="") {
                        transBox.set(count,"Z");
                        count++;
                    }
                    break;
                case R.id.dlt:
                    if (count>0){
                        transBox.set(count-1,"");
                        count--;
                    }
                    break;
            }
            recyclerViewAdapter.notifyDataSetChanged();
            answerChecking();
        }
    }
}
