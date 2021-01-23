package id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.repository;

import androidx.lifecycle.LiveData;

import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.model.Quote;

public interface QuoteRepositoryInterface {
    void insertQuote(String title);
    LiveData<Quote> getQuote();
}
