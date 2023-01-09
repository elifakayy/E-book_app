package com.example.ebookapp.repository.RemoteDB;

import android.app.Application;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.ebookapp.repository.LocalDB.DB.Book;
import com.example.ebookapp.repository.LocalDB.DB.BookDatabase;
import com.example.ebookapp.repository.LocalDB.DB.BooksInRoom;
import com.example.ebookapp.repository.LocalDB.model.BookModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FirebaseBook {

    private FirebaseDatabase mRealtimeDatabase;
    private FirebaseStorage mStorage;
    private static ArrayList bookList = new ArrayList<>();
    private Application application;

    public FirebaseBook(Application application){

        mRealtimeDatabase = FirebaseDatabase.getInstance();
        mStorage = FirebaseStorage
                .getInstance();
        this.application = application;

    }

    //This function takes all books' properties from realtime db.
    public void getAllBooks(){


        DatabaseReference mRealtimeRef = mRealtimeDatabase.getReference();

        mRealtimeRef.child("books").get().addOnCompleteListener(task -> {
            if (!task.isSuccessful()) {
                Log.e("firebase", "Error getting data", task.getException());
            }
            else {
                for(DataSnapshot ds: task.getResult().getChildren()){

                    BookModel book = ds.getValue(BookModel.class);
                    Log.e("firebase", "Kitap geldi");
                    bookList.add(book);

                }

            }

        });

    }

    //Firebase te bulunan kitabı indirmek için çağırılan fonksiyon
    public void downloadBooktoLocal(String firebaseDir, ProgressDialog pd, BookModel bookModel){
        Log.d("FİREBASEDİR",firebaseDir);
        StorageReference storageRef = mStorage.getReference().child(firebaseDir);

        final File rootPath = new File(Environment.getExternalStorageDirectory(), "EBOOKAPP DOWNLOADS");
        Log.d("ROOTPATH",rootPath.getAbsolutePath());
        if (!rootPath.exists()) {
            rootPath.mkdirs();
        }


        final File localFile = new File(rootPath, bookModel.getKitap()+".txt");
        Log.d("LOCALFILE",localFile.getName());
        storageRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener <FileDownloadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                Log.e("firebase ", ";local tem file created  created " + localFile.getAbsolutePath());


                if (localFile.canRead()){

                    //Lets add the book the room DBH
                    addBook(bookModel,localFile);

                    pd.dismiss();
                }

                Toast.makeText(application, "Download Completed - Internal storage/EBOOKAPP DOWNLOADS/" + bookModel.getKitap() + ".txt", Toast.LENGTH_SHORT).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                Log.e("firebase ", ";local tem file not created  created " + exception.toString() + "Book name : " + firebaseDir);
                Toast.makeText(application, "Download Incompleted", Toast.LENGTH_LONG).show();
            }
        });

    }

    //Firebase aracılığıyla indirilen kitapları room' a ekler.
    private void addBook(BookModel bookModel,File localFile) {

        class AddBook extends AsyncTask<Void, Void, String> {

            private String fileDir;
            private String bookPic;
            private String bookName;
            private String bookAuthor;
            private String isbn;
            private String bookSummary;
            private Application application;

            public AddBook(String fileDir, String bookPic, String bookName, String bookAuthor, String isbn, String bookSummary, Application application) {
                this.fileDir = fileDir;
                this.bookPic = bookPic;
                this.bookName = bookName;
                this.bookAuthor = bookAuthor;
                this.isbn = isbn;
                this.bookSummary = bookSummary;
                this.application = application;
            }

            @Override
            protected String doInBackground(Void... voids) {
                BookDatabase bookRoomDBRef = BookDatabase.getInstance(application);
                Book bookRoom = new Book(fileDir,
                        bookPic,
                        bookName,
                        bookAuthor,
                        isbn,
                        bookSummary);
                BooksInRoom.booksInRoom.add(bookRoom);
                bookRoomDBRef.bookDao().insertBook(bookRoom);
                return "The book added the library";
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                Toast.makeText(application,result,Toast.LENGTH_LONG).show();
            }
        }

        AddBook ab = new AddBook(localFile.getAbsolutePath(),
                        bookModel.getResim(),
                        bookModel.getKitap(),
                        bookModel.getYazar(),
                String.valueOf(bookModel.getISBN()),
                bookModel.getOzet(),application);
        ab.execute();
    }


    public List<BookModel> getBookList(){
        return bookList;
    }


}
