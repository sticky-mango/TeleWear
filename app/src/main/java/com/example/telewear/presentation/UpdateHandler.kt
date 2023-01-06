package com.example.telewear.presentation

import org.drinkless.td.libcore.telegram.Client
import org.drinkless.td.libcore.telegram.TdApi
import org.drinkless.td.libcore.telegram.TdApi.AuthorizationState
import java.util.concurrent.ConcurrentMap

class UpdateHandler(
    private var authState: AuthorizationState?,
    private var users: ConcurrentMap<Long, TdApi.User>,
    private var basicGroups: ConcurrentMap<Long, TdApi.BasicGroup>,
    private var supergroups: ConcurrentMap<Long, TdApi.Supergroup>,
    private var secretChats: ConcurrentMap<Int, TdApi.SecretChat>,
    private var chats: ConcurrentMap<Long, TdApi.Chat>
) : Client.ResultHandler {


    override fun onResult(obj: TdApi.Object?) {
        if (obj == null) {
            return
        }

        when (obj.constructor) {
            TdApi.UpdateAuthorizationState.CONSTRUCTOR -> {
                onAuthStateUpdated((obj as? TdApi.UpdateAuthorizationState)?.authorizationState)
            }

            TdApi.UpdateUser.CONSTRUCTOR -> {
                val updateUser = obj as? TdApi.UpdateUser ?: return
                users[updateUser.user.id] = updateUser.user
            }

            TdApi.UpdateUserStatus.CONSTRUCTOR -> {
                val updateUserStatus = obj as? TdApi.UpdateUserStatus ?: return
                val user = users[updateUserStatus.userId] ?: return

                synchronized(user) {
                    user.status = updateUserStatus.status
                }
            }

            TdApi.UpdateBasicGroup.CONSTRUCTOR -> {
                val updateBasicGroup = obj as? TdApi.UpdateBasicGroup ?: return
                basicGroups[updateBasicGroup.basicGroup.id] = updateBasicGroup.basicGroup
            }

            TdApi.UpdateSupergroup.CONSTRUCTOR -> {
                val updateSupergroup = obj as? TdApi.UpdateSupergroup ?: return
                supergroups[updateSupergroup.supergroup.id] = updateSupergroup.supergroup
            }

            TdApi.UpdateSecretChat.CONSTRUCTOR -> {
                val updateSecretChat = obj as? TdApi.UpdateSecretChat ?: return
                secretChats[updateSecretChat.secretChat.id] = updateSecretChat.secretChat
            }

            TdApi.UpdateNewChat.CONSTRUCTOR -> {
                val updateNewChat = obj as? TdApi.UpdateNewChat ?: return
                val chat = updateNewChat.chat

                synchronized (chat) {
                    chats[chat.id] = chat

                    val positions = chat.positions;
                    chat.positions = arrayOf<TdApi.ChatPosition>()
                    setChatPositions(chat, positions);
                }
            }

            TdApi.UpdateChatTitle.CONSTRUCTOR -> {
                val updateChat = obj as? TdApi.UpdateChatTitle ?: return
                val chat = chats[updateChat.chatId] ?: return

                synchronized (chat) {
                    chat.title = updateChat.title;
                }
            }

            TdApi.UpdateChatPhoto.CONSTRUCTOR -> {
                val updateChat = obj as? TdApi.UpdateChatPhoto ?: return
                val chat = chats[updateChat.chatId] ?: return

                synchronized (chat) {
                    chat.photo = updateChat.photo;
                }
            }

            TdApi.UpdateChatLastMessage.CONSTRUCTOR -> {
                val updateChat = obj as? TdApi.UpdateChatLastMessage ?: return
                val chat = chats[updateChat.chatId] ?: return

                synchronized (chat) {
                    chat.lastMessage = updateChat.lastMessage;
                    setChatPositions(chat, updateChat.positions);
                }
            }

            //TODO Handle next cases
        }
    }

    fun onAuthStateUpdated(state: TdApi.AuthorizationState?) {
        if (state != null) {
            authState = state
        }
    }

    fun setChatPositions(chat : TdApi.Chat, positions : Array<TdApi.ChatPosition>){

    }
}

