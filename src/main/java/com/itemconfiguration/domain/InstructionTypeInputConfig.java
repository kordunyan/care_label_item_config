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
	private String insertType;
	private String codeColumnIdx;
	private String codeColumnName;
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
