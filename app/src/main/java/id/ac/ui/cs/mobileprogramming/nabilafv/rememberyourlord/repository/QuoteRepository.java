package id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.repository;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.UUID;

import javax.inject.Inject;

import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.dao.QuoteDao;
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.model.Quote;

public class QuoteRepository implements QuoteRepositoryInterface {
    private QuoteDao quoteDao;

    @Inject
    public QuoteRepository(QuoteDao quoteDao) {
        this.quoteDao = quoteDao;
    }

    public void insertQuote(String title) {
        Quote quote = new Quote(UUID.randomUUID().toString(), title);
        new InsertQuoteAsyncTask(quoteDao).execute(quote);
    }

    private static class InsertQuoteAsyncTask extends AsyncTask<Quote, Void, Void> {
        private QuoteDao quoteDao;
        private InsertQuoteAsyncTask(QuoteDao quoteDao) {
            this.quoteDao = quoteDao;
        }

        @Override
        protected Void doInBackground(Quote... quotes) {
            quoteDao.insertQuote(quotes[0]);
            return null;
        }
    }

    public Quote getQuote() {return quoteDao.getQuote();}
}
