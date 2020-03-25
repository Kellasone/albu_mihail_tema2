package com.example.myapplication;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class StudentDAO_Impl implements StudentDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfStudent;

  private final SharedSQLiteStatement __preparedStmtOfDelete;

  public StudentDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfStudent = new EntityInsertionAdapter<Student>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `Student`(`name`,`mark`) VALUES (?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Student value) {
        if (value.name == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.name);
        }
        stmt.bindDouble(2, value.mark);
      }
    };
    this.__preparedStmtOfDelete = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM student WHERE name = ?";
        return _query;
      }
    };
  }

  @Override
  public void insert(final Student student) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfStudent.insert(student);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public int delete(final String studentName) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDelete.acquire();
    int _argIndex = 1;
    if (studentName == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, studentName);
    }
    __db.beginTransaction();
    try {
      final int _result = _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
      __preparedStmtOfDelete.release(_stmt);
    }
  }

  @Override
  public List<Student> findAll() {
    final String _sql = "SELECT name, mark FROM student";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false);
    try {
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfMark = CursorUtil.getColumnIndexOrThrow(_cursor, "mark");
      final List<Student> _result = new ArrayList<Student>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Student _item;
        _item = new Student();
        _item.name = _cursor.getString(_cursorIndexOfName);
        _item.mark = _cursor.getFloat(_cursorIndexOfMark);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
