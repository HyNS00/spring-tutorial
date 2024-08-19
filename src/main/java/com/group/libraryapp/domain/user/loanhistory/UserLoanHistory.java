package com.group.libraryapp.domain.user.loanhistory;

import com.group.libraryapp.domain.user.User;

import javax.persistence.*;

@Entity
public class UserLoanHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;
    // User와 연결을 시도
    @ManyToOne
    private User user;

    private String bookName;

    private boolean isReturn;
    protected UserLoanHistory() {
    }

    public UserLoanHistory(User user, String bookName) {
        this.user = user;
        this.bookName = bookName;
        this.isReturn = false;
    }

    //대출 상태를 true로 바꿔줌
    public void doReturn(){
        this.isReturn = true;
    }
}
