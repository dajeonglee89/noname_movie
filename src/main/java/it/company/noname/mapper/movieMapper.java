package it.company.noname.mapper;

import java.util.List;

import it.company.noname.domain.MovieCommentVO;

public interface movieMapper {
	
	// 전체 후기 가져오기
	public List<MovieCommentVO> getComments(String title);
	
	// 후기 한개 가져오기
	public MovieCommentVO getComment(int num);
	
	// 후기 한개 등록
	public void insertComment(MovieCommentVO vo);
	
	// 후기 한개 삭제
	public void deleteComment(int num);
	
	// 후기 한개 수정
	public void updateComment(MovieCommentVO vo);
	
	public void insertSearchData(String search);
	
} // movieMapper
