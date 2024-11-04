package com.example.lab3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Main activity class which manages the main user interface of the application.
 * This one helps the activity will display the list of posters for movies and aloows the user to add the selected poster and movies which the user can add it to a watch list
 */
public class MainActivity extends AppCompatActivity implements PostersListener{

    private Button buttonAddToWatchlist;

    /**Called when the activity is starting.
     * Initializes the user interface sets up the data for the poster
     *
     * @param savedInstanceState if the activity is being re-initialized after previously being shut down, this bundle contains the most recent data
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
      //  ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
       //     Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
       //     v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
       //     return insets;
     //   });
//set up the Recyclerview and button for watch list
        RecyclerView postersRecyclerView = findViewById(R.id.posterRecyclerView);
        buttonAddToWatchlist = findViewById(R.id.buttonAddToWatchlist);



        //preapre data
        List<Poster> posterList = new ArrayList<>();
/**
 * This one will add the movie with the image and the movie name, rating and also created by who and the story about that
 */
        Poster USmovie1 = new Poster();
        USmovie1.image = R.drawable.avenger;
        USmovie1.name = "AVENGER";
        USmovie1.rating = 4.5f;
        USmovie1.createdBy = "Stan Lee";
        USmovie1.story = " This is about the superhero movie";
        posterList.add(USmovie1);

        Poster USmovie2 = new Poster();
        USmovie2.image = R.drawable.batman;
        USmovie2.name = "BATMAN";
        USmovie2.rating = 4.7f;
        USmovie2.createdBy = "Bob Kane";
        USmovie2.story = " This is about batman fighting with Joker";
        posterList.add(USmovie2);

        Poster USmovie3 = new Poster();
        USmovie3.image = R.drawable.coco;
        USmovie3.name = "COCO";
        USmovie3.rating = 5f;
        USmovie3.createdBy = "Pixar Animation Studios";
        USmovie3.story = " This is about the family movie";
        posterList.add(USmovie3);

        Poster USmovie4 = new Poster();
        USmovie4.image = R.drawable.download1;
        USmovie4.name = "DEADPOOL";
        USmovie4.rating = 4.5f;
        USmovie4.createdBy = "Tim Miller";
        USmovie4.story = " This is about the deadpool movie";
        posterList.add(USmovie4);

        Poster USmovie5 = new Poster();
        USmovie5.image = R.drawable.download2;
        USmovie5.name = "JOKER";
        USmovie5.rating = 4.5f;
        USmovie5.createdBy = "Todd Phillips";
        USmovie5.story = " This is about the joker movie";
        posterList.add(USmovie5);

        Poster VNmovie = new Poster();
        VNmovie.image = R.drawable.download3;
        VNmovie.name = "BO GIA";
        VNmovie.rating = 4.5f;
        VNmovie.createdBy = "TRAN THANH";
        VNmovie.story = " This is about DAD movie";
        posterList.add(VNmovie);

        Poster USmovie6 = new Poster();
        USmovie6.image = R.drawable.download4;
        USmovie6.name = "PETER PAN";
        USmovie6.rating = 4f;
        USmovie6.createdBy = "Disney";
        USmovie6.story = " This is about the peter pan movie";
        posterList.add(USmovie6);

        Poster Korean = new Poster();
        Korean.image = R.drawable.download5;
        Korean.name = "QUEEN OF TEARS";
        Korean.rating = 5f;
        Korean.createdBy = "Netflix";
        Korean.story = " This is about the queen who have the power in her house ";
        posterList.add(Korean);

        Poster USmovie7 = new Poster();
        USmovie7.image = R.drawable.download6;
        USmovie7.name = "FAST AND FURIOUS";
        USmovie7.rating = 5f;
        USmovie7.createdBy = "Justin Lin";
        USmovie7.story = " This is about the car movie";
        posterList.add(USmovie7);

        Poster VNmovie2 = new Poster();
        VNmovie2.image = R.drawable.download3;
        VNmovie2.name = "NHA BA NU";
        VNmovie2.rating = 3.5f;
        VNmovie2.createdBy = "TRAN THANH";
        VNmovie2.story = " This is about Husband movie";
        posterList.add(VNmovie);




        final PosterAdapter posterAdapter = new PosterAdapter(posterList, this);
        postersRecyclerView.setAdapter(posterAdapter);

//set the lister for add to the watchlist button
        buttonAddToWatchlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Poster> selectPosters = posterAdapter.getSelectedPoster();
                StringBuilder posterNames = new StringBuilder();
                for(int i =0; i < selectPosters.size(); i++) {
                    if(i==0) {
                        posterNames.append(selectPosters.get(i).name);
                    }
                    else {
                        posterNames.append("\n").append(selectPosters.get(i).name);
                    }
                }
                Toast.makeText(MainActivity.this, posterNames.toString(), Toast.LENGTH_SHORT).show();
            }
        });




    }

    /**
     * This method to cal when a poster's selection state changes. It updates the visibility of the "add to watchlist"
     * @param isSelected Boolean value indication if at least one poster is selected. If true, the button becomes visible
     *                   otherwise, it is hidden
     */

    @Override
    public void onPosterAction(Boolean isSelected) {
        if(isSelected) {
            buttonAddToWatchlist.setVisibility(View.VISIBLE);

        }else {
            buttonAddToWatchlist.setVisibility(View.GONE);
        }
    }
}