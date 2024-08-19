package com.group.libraryapp.service.book;


import com.group.libraryapp.domain.book.Book;
import com.group.libraryapp.domain.book.BookRepository;
import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.domain.user.UserRepository;
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistory;
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistoryRepository;
import com.group.libraryapp.dto.book.request.BookCreateRequest;
import com.group.libraryapp.dto.book.request.BookLoanRequest;
import com.group.libraryapp.dto.book.request.BookReturnRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor // final로 선언된 필드나 @NonNull로 표시된 필드에 대해 생성자를 자동으로 만든다.
// 만약 모든 필드에 대한 생성자를 원한다면, @AllArgsConstructor 어노테이션을 사용할 수 있다.
public class BookService {
    private final BookRepository bookRepository;
    private final UserLoanHistoryRepository userLoanHistoryRepository;
    private final UserRepository userRepository;

    @Transactional
    public void saveBook(BookCreateRequest request){
        bookRepository.save(new Book(request.getName()));
    }

    @Transactional
    public void loanBook(BookLoanRequest request){
        // 책의이름을 가지고 존재하는지 확인
       Book book = bookRepository.findByName(request.getBookName())
                .orElseThrow(IllegalArgumentException::new);


       // 대출 여부를 확인
       if(userLoanHistoryRepository.existsByBookNameAndIsReturn(book.getName(),false)){
           throw new IllegalArgumentException("대출되어 있는 책입니다.");
       }
       // 유저정보를 가져온다.
        User user = userRepository.findByName(request.getUserName())
                .orElseThrow(IllegalArgumentException::new);
        user.loanBook(book.getName());
       // 유저정보와 책 정보를 기반으로 UserLoanHistory를 저장
//        userLoanHistoryRepository.save(new UserLoanHistory(user, book.getName()));
    }

    @Transactional
    public void returnBook(BookReturnRequest request){
        // 유저를 체크
        User user = userRepository.findByName(request.getUserName())
                .orElseThrow(IllegalArgumentException::new);
        user.returnBook(request.getBookName());
    }
}
