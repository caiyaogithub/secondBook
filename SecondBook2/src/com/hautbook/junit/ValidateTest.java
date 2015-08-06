package com.hautbook.junit;

import static org.junit.Assert.*;

import org.junit.Test;

import com.hautbook.util.Validate;

public class ValidateTest {

	@Test
	public void testValidateDept() {
		fail("Not yet implemented");
	}

	@Test
	public void testValidateName() {
		fail("Not yet implemented");
	}

	@Test
	public void testValidateEmail() {
		fail("Not yet implemented");
	}

	@Test
	public void testValidatePassword() {
		fail("Not yet implemented");
	}

	@Test
	public void testValidateImageType() {
		fail("Not yet implemented");
	}

	@Test
	public void testValidatePost() {
		fail("Not yet implemented");
	}

	@Test
	public void testValidateUserName() {
		assertEquals(false, Validate.validateUserName("��Ϣ��ѧ�빤��ѧԺ_caiyao"));
	}

	@Test
	public void testValidateId() {
		assertEquals(false, Validate.validateId("S201411310001"));
	}

	@Test
	public void testValidateType() {
		assertEquals(false, Validate.validateType("S"));
	}

	@Test
	public void testValidateBookName() {
		assertEquals(true, Validate.validateBookName("�������ѧ���ۣ�������123��"));
	}

	@Test
	public void testValidateAuthor() {
		assertEquals(true, Validate.validateAuthor("���ܿ�"));
	}

	@Test
	public void testValidatePoster() {
		assertEquals(true , Validate.validatePoster("�Ϻ�����������") );
	}

	@Test
	public void testValidateIsbn() {
		assertEquals(true, Validate.validateIsbn("xxx")) ;
	}

	@Test
	public void testValidateCategory() {
		fail("Not yet implemented");
	}

	@Test
	public void testValidateDesc() {
		fail("Not yet implemented");
	}

	@Test
	public void testValidatePrice() {
		fail("Not yet implemented");
	}

	@Test
	public void testValidateLinkmanName() {
		fail("Not yet implemented");
	}

	@Test
	public void testValidateLinkmanTel() {
		fail("Not yet implemented");
	}

	@Test
	public void testValidateStatus() {
		fail("Not yet implemented");
	}

}
