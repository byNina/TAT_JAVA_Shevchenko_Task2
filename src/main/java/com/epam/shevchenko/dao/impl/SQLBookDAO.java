package com.epam.shevchenko.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.shevchenko.bean.Book;
import com.epam.shevchenko.dao.BookDAO;
import com.epam.shevchenko.enums.TableMapping;

public class SQLBookDAO extends SQLBaseDAO<Book> implements BookDAO {

	private static final String SELECT_ALL_SQL = "SELECT * FROM library.books";
	private static final String UPDATE_BOOK_SQL = "UPDATE library.books SET title=?, author=? WHERE id=?";

	@Override
	protected String getSelectQuery() {
		return SELECT_ALL_SQL;
	}

	@Override
	protected String getUpdateQuery() {
		return UPDATE_BOOK_SQL;
	}

	@Override
	protected List<Book> parseResultSet(ResultSet rs) throws SQLException {
		List<Book> result = new ArrayList<Book>();
		Book book ;
		while (rs.next()) {
			book = new Book();
			book.setId(rs.getInt(TableMapping.COLUMN_NAME_BOOK_ID));
			book.setTitle(rs.getString(TableMapping.COLUMN_NAME_BOOK_TITLE));
			book.setAuthor(rs.getString(TableMapping.COLUMN_NAME_BOOK_AUTHOR));
			result.add(book);
		}
		return result;
	}

	@Override
	protected PreparedStatement updateStatement(PreparedStatement prStatement, Book book) throws SQLException {
		if (prStatement != null) {
			prStatement.setString(1, book.getTitle());
			prStatement.setString(2, book.getAuthor());
			prStatement.setLong(3, book.getId());
		}
		return prStatement;
	}

}
