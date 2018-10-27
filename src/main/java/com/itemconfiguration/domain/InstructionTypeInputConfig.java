package com.itemconfiguration.domain;

import javax.persistence.*;
import javax.xml.crypto.Data;
import java.util.List;

@Entity
public class InstructionTypeInputConfig {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(
			name = "instruction_type_name",
			foreignKey = @ForeignKey(name = "instruction_type_input_config_instruction_type_name_fkey")
	)
	private DataType dataType;
	private Boolean useFtp;
	private String ftpServer;
	private String ftpUser;
	private String ftpPassword;
	private String ftpFolder;
	private String fileNamePattern;
	private String workFolder;
	private String sheetName;
	private Integer sheetIdx;
	private String insertType;
	private String codeColumnIdx;
	private String codeColumnName;
	private Integer expectedColumnsQty;
	private Integer expectedSheetsQty;
	private Integer headerIdx;
	private Integer dataRowIdx;
	private String deleteCodeColumnIdx;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	@JoinColumn(
			name = "instruction_type_input_config_id",
			foreignKey = @ForeignKey(name = "instruction_type_language_inp_instruction_type_input_config_fkey")
	)
	private List<InstructionTypeLanguageInputPosition> instructionTypeLanguageInputPositions;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DataType getDataType() {
		return dataType;
	}

	public void setDataType(DataType dataType) {
		this.dataType = dataType;
	}

	public Boolean getUseFtp() {
		return useFtp;
	}

	public void setUseFtp(Boolean useFtp) {
		this.useFtp = useFtp;
	}

	public String getFtpServer() {
		return ftpServer;
	}

	public void setFtpServer(String ftpServer) {
		this.ftpServer = ftpServer;
	}

	public String getFtpUser() {
		return ftpUser;
	}

	public void setFtpUser(String ftpUser) {
		this.ftpUser = ftpUser;
	}

	public String getFtpPassword() {
		return ftpPassword;
	}

	public void setFtpPassword(String ftpPassword) {
		this.ftpPassword = ftpPassword;
	}

	public String getFtpFolder() {
		return ftpFolder;
	}

	public void setFtpFolder(String ftpFolder) {
		this.ftpFolder = ftpFolder;
	}

	public String getFileNamePattern() {
		return fileNamePattern;
	}

	public void setFileNamePattern(String fileNamePattern) {
		this.fileNamePattern = fileNamePattern;
	}

	public String getWorkFolder() {
		return workFolder;
	}

	public void setWorkFolder(String workFolder) {
		this.workFolder = workFolder;
	}

	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}

	public Integer getSheetIdx() {
		return sheetIdx;
	}

	public void setSheetIdx(Integer sheetIdx) {
		this.sheetIdx = sheetIdx;
	}

	public String getInsertType() {
		return insertType;
	}

	public void setInsertType(String insertType) {
		this.insertType = insertType;
	}

	public String getCodeColumnIdx() {
		return codeColumnIdx;
	}

	public void setCodeColumnIdx(String codeColumnIdx) {
		this.codeColumnIdx = codeColumnIdx;
	}

	public String getCodeColumnName() {
		return codeColumnName;
	}

	public void setCodeColumnName(String codeColumnName) {
		this.codeColumnName = codeColumnName;
	}

	public Integer getExpectedColumnsQty() {
		return expectedColumnsQty;
	}

	public void setExpectedColumnsQty(Integer expectedColumnsQty) {
		this.expectedColumnsQty = expectedColumnsQty;
	}

	public Integer getExpectedSheetsQty() {
		return expectedSheetsQty;
	}

	public void setExpectedSheetsQty(Integer expectedSheetsQty) {
		this.expectedSheetsQty = expectedSheetsQty;
	}

	public Integer getHeaderIdx() {
		return headerIdx;
	}

	public void setHeaderIdx(Integer headerIdx) {
		this.headerIdx = headerIdx;
	}

	public Integer getDataRowIdx() {
		return dataRowIdx;
	}

	public void setDataRowIdx(Integer dataRowIdx) {
		this.dataRowIdx = dataRowIdx;
	}

	public String getDeleteCodeColumnIdx() {
		return deleteCodeColumnIdx;
	}

	public void setDeleteCodeColumnIdx(String deleteCodeColumnIdx) {
		this.deleteCodeColumnIdx = deleteCodeColumnIdx;
	}

	public List<InstructionTypeLanguageInputPosition> getInstructionTypeLanguageInputPositions() {
		return instructionTypeLanguageInputPositions;
	}

	public void setInstructionTypeLanguageInputPositions(List<InstructionTypeLanguageInputPosition> instructionTypeLanguageInputPositions) {
		this.instructionTypeLanguageInputPositions = instructionTypeLanguageInputPositions;
	}
}
