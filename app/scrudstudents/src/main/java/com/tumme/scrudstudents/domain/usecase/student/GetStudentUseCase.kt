package com.tumme.scrudstudents.domain.usecase.student

import com.tumme.scrudstudents.data.repository.StudentRepository

class GetStudentsUseCase(private val repo: StudentRepository) {
    operator fun invoke() = repo.getAllStudents()
}