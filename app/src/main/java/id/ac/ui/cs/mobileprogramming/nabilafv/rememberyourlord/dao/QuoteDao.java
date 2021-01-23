package id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.model.Quote;

@Dao
public interface QuoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertQuote(Quote quote);

    @Query("SELECT * FROM quote LIMIT 1")
    LiveData<Quote> getQuote();
}
