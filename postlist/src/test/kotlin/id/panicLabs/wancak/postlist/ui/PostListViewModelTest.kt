package id.panicLabs.wancak.postlist.ui

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import id.panicLabs.core.repository.CoreRepository
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.jetbrains.spek.subject.SubjectSpek
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith

/**
 * @author panicLabs
 * @createdOn 27/07/2018
 */
@RunWith(JUnitPlatform::class)
class PostListViewModelTest : SubjectSpek<PostListViewModel>(
        {
            val repository: CoreRepository = mock()
            subject {
                PostListViewModel(repository)
            }

            given("Post List ViewModel Interactions"){
                on("FetchData postList") {
                    it("will update news through the use case") {
                        subject.fetchData("lol")
                        verify(repository).fetchSection("lol")
                    }
                }

                on("Observing postList") {
                    it("will observe data list through repository") {
                        subject.observeSection()
                        verify(repository).observeSection()
                    }
                }

                on("Observing error") {
                    it("will observe error through repository") {
                        subject.observeError()
                        verify(repository).observeError()
                    }
                }
            }

        }
)